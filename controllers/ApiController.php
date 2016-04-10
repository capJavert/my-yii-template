<?php

namespace app\controllers;

use app\models\File;
use app\models\Ispostava;
use app\models\Poslovi;
use app\models\TimClanoviForm;
use app\models\User;
use app\models\Timovi;
use app\models\UserForm;
use app\models\UsersPoslovi;
use app\models\UsersTimovi;
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

        $name = Yii::$app->request->get('modelName');

        switch(Yii::$app->request->get('modelName')) {
            case 'User':
                $user = new User();
                $user = $user->findByToken(Yii::$app->request->get('token'));
              //  $ispostava=new Ispostava();
                //    $array["lokacija"]="nekej";
                $model = new UserForm($user->ime,$user->prezime,
                $user->username,$user->auth_key,$user->oib,$user->dat_rod,
                $user->adresa_stanovanja,$user->mjesto_stanovanja,$user->broj_tel,
                $user->mob,$user->napomena,$user->getIdIspostava()->one()->lokacija,
            $user->broj_sati);
                foreach($user->getUsersPoslovis()->all() as $poslovi)
                {
                    $posao=Poslovi::find()->where(["id_posao"=>$poslovi->id_posao])->one();
                    $temp["posao"]=$posao->naziv;
                    $temp["prioritet"]=$poslovi->prioritet;
                    array_push($model->jobs,$temp);

                }

                return BaseJson::encode($model);
                break;

                /*
                $models = User::find()->all();

                $data = [];
                foreach($models as $model)
                    $data[$name][] = $model->username;

                //var_dump($data) or die;
                break;
                */
            case 'Shift':
                $user = new User();
                $user = $user->findByToken(Yii::$app->request->get('token'));
             //  return BaseJson::encode([$userTimovi,$users]);
                break;
            case 'Timovi':
                $user = new User();
                $user = $user->findByToken(Yii::$app->request->get('token'));
                $user_timovi= UsersTimovi::find()->where(["id_user" => $user->id])->all();
                $array["raspored"] = array();
                foreach($user_timovi as $user_posao)
                {
                   $tim["dan"]=$user_posao->dan;
                    $tim["tim"]=$user_posao->id_tim;
                    $tim["posao"]=$user_posao->posao;
                    $tim["smjena"]=$user_posao->smjena;
                    $tims=Timovi::find()->where(["id_tim"=>$user_posao->id_tim])->one();
                    $ispostava=Ispostava::find()->where(["id_ispostava" => $tims->id_ispostava])->one();
                    $tim["ispostava"]=$ispostava->lokacija;
                    array_push($array["raspored"],$tim);
                }
                return BaseJson::encode($array);
                break;


            default: return BaseJson::encode(['errors'=>'ERROR_INVALID_MODEL_NAME']);
        }

   //     return BaseJson::encode($data);
    }

    public function actionTestgcm() {
        /* @var $apnsGcm \bryglen\apnsgcm\Gcm */
        $gcm = Yii::$app->gcm;

        $tokens = [
          'f4qCXUaakIk:APA91bGdiFKym-Kizf_5K9tz05FFMlVO36nLvDwNJjkzak7STSgulLaCMeEZguN54zYikp23P0KCkys7zTZRaV5C8XsU_iY76o33mDDYa2r0D_Cq6w8SdZjJmSBmYzFPCgiusE8RkNQT'
        ];

        $message = ['title'=>'PRO', 'message'=>'good night'];

        $gcm->send('f4qCXUaakIk:APA91bGdiFKym-Kizf_5K9tz05FFMlVO36nLvDwNJjkzak7STSgulLaCMeEZguN54zYikp23P0KCkys7zTZRaV5C8XsU_iY76o33mDDYa2r0D_Cq6w8SdZjJmSBmYzFPCgiusE8RkNQT',
            BaseJson::encode($message)
        );

        return 1;
    }

    public function actionUpload() {
        if ($_FILES) {
            $target_dir = "images/";
            $target_file = $target_dir . basename($_FILES["picture"]["name"]);
            // Check if image file is a actual image or fake image
            $check = getimagesize($_FILES["picture"]["tmp_name"]);
            if (move_uploaded_file($_FILES["picture"]["tmp_name"], $target_file)) {
                return BaseJson::encode(['file'=>'SUCCESS_UPLOADED']);
            } else
                return BaseJson::encode(['error'=>'ERROR_NO_FILE']);
        } else
            return BaseJson::encode(['error'=>'ERROR_NO_FILE']);
    }
}
