<?php

namespace app\controllers;

use app\models\Ispostava;
use app\models\Timovi;
use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\filters\VerbFilter;
use app\models\User;
use app\models\LoginForm;
use app\models\SignupForm;
use yii\helpers\ArrayHelper;

class SiteController extends Controller
{
    public function behaviors()
    {
        return [
            'access' => [
                'class' => AccessControl::className(),
                'only' => ['logout'],
                'rules' => [
                    [
                        'actions' => ['logout', 'schedule', 'scheduledetail'],
                        'allow' => true,
                        'roles' => ['@'],
                    ],
                ],
            ],
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    //'logout' => ['post'],
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

    public function actionIndex()
    {
        return $this->render('index');
    }

    public function actionLogin()
    {
        if (!Yii::$app->user->isGuest) {
            return $this->goHome();
        }

        $model = new LoginForm();
        if ($model->load(Yii::$app->request->post()) && $model->login()) {
            return $this->goBack();
        }
        return $this->render('login', [
            'model' => $model,
        ]);
    }

    public function actionLogout()
    {
        Yii::$app->user->logout();

        return $this->goHome();
    }

    /**
     * Signs user up.
     *
     * @return mixed
     */
    public function actionSignup()
    {
        $model = new SignupForm();
        if ($model->load(Yii::$app->request->post())) {
            if ($user = $model->signup()) {
                if (Yii::$app->getUser()->login($user)) {
                    return $this->goHome();
                }
            }
        }

        return $this->render('signup', [
            'model' => $model,
        ]);
    }

    /**
     * List schedule.
     *
     * @return mixed
     */
    public function actionSchedule()
    {
        //$workers = User::find()->all();

        return $this->render('schedule', [

        ]);
    }

    /**
     * List info for selected day.
     *
     * @return mixed
     */
    public function actionScheduledetail()
    {
        $dayId = Yii::$app->request->get('day');

        $ispostave = Ispostava::find()->where('id_ispostava>1')->all();
        $listIspostave = ArrayHelper::map($ispostave, 'id_ispostava', 'lokacija');

        $timovi = Timovi::find()->all();
        $listTimovi = ArrayHelper::map($timovi, 'id_tim', 'id_tim');

        $listSmjene = [0=>'Dan', 1=>'Nocna'];

        //$workers = User::find()->all();

        return $this->render('scheduleDetail', [
            'dayId'=>$dayId,
            'listIspostave'=>$listIspostave,
            'listTimovi'=>$listTimovi,
            'listSmjene'=>$listSmjene
        ]);
    }
}
