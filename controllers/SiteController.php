<?php

namespace app\controllers;

use app\models\Ispostava;
use app\models\RasporedForm;
use app\models\rasporedPrikaz;
use app\models\Timovi;
use app\models\UsersTimovi;
use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\filters\VerbFilter;
use app\models\User;
use app\models\LoginForm;
use app\models\SignupForm;
use yii\helpers\ArrayHelper;
use yii\data\ActiveDataProvider;
use yii\data\ArrayDataProvider;

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
        $userTimovi= UsersTimovi::find()->where(["dan"=>$dayId])->orderBy("id_tim")->all();
        $listaUsera= array();
        $listActiveTeams= array();
        $raspored= array();
        $temp=-1;
        $timID = Yii::$app->request->get('timID');
        foreach($userTimovi as $userTim)
        {

                $user = User::find()->where(["id" => $userTim->id_user])->one();
                $tim = Timovi::find()->where(["id_tim" => $userTim->id_tim])->one();
                $ispostave = Ispostava::find()->where(["id_ispostava" => $tim->id_ispostava])->one();
                $naziv = "";
                if (strpos($userTim->posao, "M") !== false) {
                    $naziv = "Medicinski liječnik";

                }
                if (strpos($userTim->posao, "T") !== false) {
                    $naziv = "Medicinski tehničar";

                }
                if (strpos($userTim->posao, "V") !== false) {
                    $naziv = "Vozač";

                }

                if (strpos($userTim->posao, "D") !== false) {
                    $naziv = "Dispačer";

                }
                if ($userTim->smjena == 1) {
                    $smjena = "Noćna";
                } else {
                    $smjena = "Dnevna";
                }

            if(!isset($_GET["timID"]) && !isset($_GET["ispostaveID"]) && !isset($_GET["smjenaID"]))
            {
                array_push(
                    $raspored,
                    new RasporedForm(
                        $ispostave->lokacija, $tim->id_tim, $user->ime
                        . " " . $user->prezime, $smjena, $naziv
                    )
                );
            }
           else if(isset($_GET["timID"])) {
               if($_GET["timID"]==$userTim->id_tim)
               array_push(
                   $raspored,
                   new RasporedForm(
                       $ispostave->lokacija, $tim->id_tim, $user->ime
                       . " " . $user->prezime, $smjena, $naziv
                   )
               );
           }
            else if(isset($_GET["ispostaveID"]))
            {
                if($_GET["ispostaveID"]==$ispostave->id_ispostava)
                array_push(
                    $raspored,
                    new RasporedForm(
                        $ispostave->lokacija, $tim->id_tim, $user->ime
                        . " " . $user->prezime, $smjena, $naziv
                    )
                );
            }
            else if(isset($_GET["smjenaID"]))
            {
                if($_GET["smjenaID"]==$userTim->smjena)
                array_push(
                    $raspored,
                    new RasporedForm(
                        $ispostave->lokacija, $tim->id_tim, $user->ime
                        . " " . $user->prezime, $smjena, $naziv
                    )
                );
            }



            /*
            $user= User::find()->where(["id" => $userTim->id_user])->one();
            $useri= array();
            if($userTim->id_tim!=$temp) {
                $tim = Timovi::find()->where(["id_tim" => $userTim->id_tim])->one();
                $rasp= new RasporedForm();
                $rasp->tim=$tim->id;
                $rasp->ispostava= Ispostava::find()->where(["id_ispostava"=>$tim->id_tim]);
            }
            array_push($useri,$user);

            $temp=$userTim->id_tim;
            */
        }


        $dataProvider = new ArrayDataProvider([
            'allModels' => $raspored,
            'pagination' => [
                'pageSize' => 10,
            ],
        ]);
        //$workers = User::find()->all();

        return $this->render('scheduleDetail', [
            'dayId'=>$dayId,
            'listIspostave'=>$listIspostave,
            'listTimovi'=>$listTimovi,
            'listSmjene'=>$listSmjene,
            'userTimovi'=>$userTimovi,
            'dataProvider'=>$dataProvider,
        ]);
    }

    public function actionGenerate()
    {
        /*
        if (!Yii::$app->user->isGuest) {
            return $this->goHome();
        }
        */
        $model= new rasporedPrikaz();
        return $this->render('generate', [
            'model' => $model,
        ]);
    }
}
