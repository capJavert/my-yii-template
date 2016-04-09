<?php

namespace app\controllers;
use app\models\Datoteka;
use app\models\File;
use app\models\User;
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

    public function actionParse()
    {
        $fileContent= file_get_contents('d.txt');
        $s= BaseJson::encode($fileContent);
        $arr= explode('\n',$s);
        $model = new Datoteka();
        $repl= array('"','\r');
        $model->broj_tjedana= str_replace($repl, "", $arr[0]);
        $model->broj_ispostava = str_replace($repl, "", $arr[1]);
        echo str_replace($repl, "", $arr[0]);
     //   $model->save();
        var_dump($arr);
        foreach ($arr as $a)
        {

        }

    }



}
