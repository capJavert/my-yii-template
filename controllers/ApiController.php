<?php

namespace app\controllers;

use app\models\User;
use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\filters\VerbFilter;
use app\models\LoginForm;
use app\models\SignupForm;
use yii\helpers\BaseJson;

class ApiController extends Controller
{
    public function behaviors()
    {
        Yii::$app->request->enableCsrfValidation = false;

        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'logout' => ['get'],
                    'signup' => ['get'],
                    'find' => ['get'],
                    'login' => ['get'],
                    'upload' => ['post'],
                ],
            ],
        ];
    }

    public function actions()
    {
        return [
            'error' => [
                'class' => 'yii\web\ErrorAction',
            ],
            'captcha' => [
                'class' => 'yii\captcha\CaptchaAction',
                'fixedVerifyCode' => YII_ENV_TEST ? 'testme' : null,
            ],
        ];
    }

    public function authentication() {
        $model = new User();

        return $model->findIdentityByAccessToken(Yii::$app->request->get('token'));
    }

    /**
     * Signs user up.
     *
     * @return string
     */

    public function actionSignup()
    {
        $model = new SignupForm();

        if ($model->load(Yii::$app->request->get(), '')) {
            if ($user = $model->signup()) {
                if (Yii::$app->getUser()->login($user)) {
                    return BaseJson::encode(['token'=>$user->getAuthKey()]);
                }
            }
        }

        $model->validate();

        return BaseJson::encode(['errors'=>$model->getErrors()]);
    }

    /**
     * Logins user up.
     *
     * @return string
     */

    public function actionLogin()
    {
        $model = new LoginForm();

        if ($model->load(Yii::$app->request->get(), '') && $model->login()) {
            return BaseJson::encode(['token'=>$model->getUser()->getAuthKey()]);
        }

        $model->validate();

        return BaseJson::encode(['errors'=>$model->getErrors()]);
    }

    /**
     * Logout user.
     *
     * @return string
     */

    public function actionLogout()
    {
        if(!$this->authentication())
            return BaseJson::encode(['errors'=>'ERROR_AUTH_FAILED']);

        $user = new User();
        $user = $user->findByToken(Yii::$app->request->get('token'));

        $user->generateAuthKey();
        $user->save();

        return BaseJson::encode(['success'=>'SUCCESS_LOGGED_OUT']);

    }

    /**
     * Find model.
     *
     * @return string
     */

    public function actionFind() {
        if(!$this->authentication())
            return BaseJson::encode(['errors'=>'ERROR_AUTH_FAILED']);

        $name = Yii::$app->request->get('modelName').'s';

        switch(Yii::$app->request->get('modelName')) {
            case 'User':
                $models = User::find()->all();

                $data = [];
                foreach($models as $model)
                    $data[$name][] = $model->username;

                //var_dump($data) or die;
                break;
            default: return BaseJson::encode(['errors'=>'ERROR_INVALID_MODEL_NAME']);
        }

        return BaseJson::encode($data);
    }

    public function actionTestgcm() {
        /* @var $apnsGcm \bryglen\apnsgcm\Gcm */
        $gcm = Yii::$app->gcm;

        $gcm->send(['12345'], 'lalalalal',
            [
                'customerProperty' => 1,
            ],
            [
                'timeToLive' => 3
            ]
        );

        return 1;
    }

    public function actionUpload() {
        if ($_FILES) {
            return BaseJson::encode($_FILES);
        } else
            return BaseJson::encode(['errors'=>'ERROR_NO_POST']);
    }
}
