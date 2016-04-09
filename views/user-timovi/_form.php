<?php

use app\models\User;
use app\models\Timovi;
use yii\helpers\Html;
use app\models\UsersTimovi;
use yii\data\ActiveDataProvider;
use yii\helpers\ArrayHelper;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\widgets\ActiveForm;


/* @var $this yii\web\View */
/* @var $model app\models\UsersTimovi */
/* @var $form yii\widgets\ActiveForm */

$users= User::find()->all();
$listUsers= ArrayHelper::map($users,'id','ime');
$timovi = Timovi::find()->all();
$listTimovi = ArrayHelper::map($timovi, 'id_tim', 'id_tim');
$listSmjene = [0=>'Dan', 1=>'Noćna'];


?>

<div class="users-timovi-form">

    <?php $form = ActiveForm::begin(); ?>

    <?=
     $form->field($model, 'id_user')
        ->dropDownList(
            $listUsers,           // Flat array ('id'=>'label')
            ['prompt'=>'Odaberi korisnika']    // options
        );
    ?>

    <?=  $form->field($model, 'id_tim')
        ->dropDownList(
            $listTimovi,           // Flat array ('id'=>'label')
            ['prompt'=>'Odaberi tim']    // options
        ); ?>

    <?= $form->field($model, 'dan')->textInput() ?>

    <?= $form->field($model, 'smjena')
        ->dropDownList(
            $listSmjene// Flat array ('id'=>'label')
        // options
        );?>

    <?=   $form->field($model, 'posao')
        ->dropDownList(
            ["D","V","M","T"]// Flat array ('id'=>'label')
               // options
        );?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
