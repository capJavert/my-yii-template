<?php

namespace app\controllers;
use app\models\Datoteka;
use app\models\File;
use app\models\Ispostava;
use app\models\Poslovi;
use app\models\Timovi;
use app\models\User;
use app\models\UsersPoslovi;
use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\filters\VerbFilter;
use app\models\LoginForm;
use app\models\SignupForm;
use yii\helpers\BaseJson;


class LogicController extends \yii\web\Controller
{
    public function behaviors()
    {
        Yii::$app->request->enableCsrfValidation = false;

        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [


                ],
            ],
        ];
    }

    private $temp=0;
    public function actionParse()
    {
        $fileContent= file_get_contents('d.txt');
        $s= BaseJson::encode($fileContent);
        $arr= explode('\n',$s);
        $model = new Datoteka();
        $repl= array('"','\r');
        $broj_ispostava= str_replace($repl, "", $arr[1]);
        $model->broj_tjedana= str_replace($repl, "", $arr[0]);
        $model->broj_ispostava= $broj_ispostava;
      //  echo str_replace($repl, "", $arr[0]);
        $model->save();
      //  var_dump($arr);
        $this->spremiIspostave($arr,$broj_ispostava);
        $broj_radnika= str_replace($repl, "", $arr[2+$broj_ispostava]);
      //  $this->spremiRadnike($broj_ispostava+3,$arr,$broj_radnika);


    }

    public function spremiIspostave($arr, $broj_ispostava)
    {
        $poc=$broj_ispostava+3;
        for($i=2;$i<$broj_ispostava+2;$i++)
        {
            $ispostave= new Ispostava();
            $ispostave->lokacija="nesto";
            $ispostave->rang=1;
            if($ispostave->validate()) $ispostave->save();
            else var_dump($ispostave->getErrors()) or die;
            $ispostaveArray=explode('\r',$arr[$i]);
            $ispostaveArray=explode(' ',$ispostaveArray[0]);
            var_dump($ispostaveArray);
            for($j=0;$j<sizeof($ispostaveArray);$j++)
            {

                $model= new Timovi();
                echo $ispostaveArray[$j];
                if (strpos($ispostaveArray[$j], "DVMT") !== false) {
                    $model->vrsta = 1;
                }
                else $model->vrsta=0;
                $model->id_ispostava=$ispostave->id_ispostava;
                if($model->validate()) $model->save();
                else var_dump($model->getErrors()) or die;

            }
            $this->spremiRadnike($poc,$arr,$ispostave->id_ispostava);
        }
    }

    public function spremiRadnike($poc,$arr,$id_ispostave)
    {

        for($i=$poc;$i<sizeof($arr);$i++)
        {

            $rad=explode(" ",$arr[$i]);
            if($rad[0]==$this->temp) {


                $radnik = new User();
                $radnik->ime = "ime" . $i;
                $radnik->prezime = "prezime" . $i;
                $radnik->oib = "oib" . $i;
                $radnik->id_ispostava = $id_ispostave;
                echo $rad[0];
                $radnik->spol = "m";
                $radnik->dat_rod = "1994-09-20";
                //  var_dump($rad[0]);

                if ($radnik->validate()) {
                    $radnik->save();
                } else {
                    var_dump($radnik->getErrors())
                    or die;
                }
                echo $radnik->id;
                $userPoslovi = new UsersPoslovi();
                $userPoslovi->id_user = $radnik->id;
                $prioritet = 1;
                //   echo $rad[1];


                $characters = str_split($rad[1]);

                for ($j = 0; $j < sizeof($characters); $j++) {
                    echo $characters[$j] . " ";
                    $userPoslovi = new UsersPoslovi();
                    $userPoslovi->id_user = $radnik->id;
                    //   echo $userPoslovi->id_user;
                    if ($characters[$j] == "D") {
                        $userPoslovi->id_posao = 1;
                    }
                    if ($characters[$j] == "T") {
                        $userPoslovi->id_posao = 4;
                    }
                    if ($characters[$j] == "V") {
                        $userPoslovi->id_posao = 3;
                    }
                    if ($characters[$j] == "M") {
                        $userPoslovi->id_posao = 2;
                    }
                    $userPoslovi->prioritet = $prioritet;
                    if ($userPoslovi->validate()) {
                        $userPoslovi->save();
                    } else {
                        var_dump($userPoslovi->getErrors()) or die;
                    }
                    $prioritet++;
                }
            }
        }
        $this->temp++;
    }





}
