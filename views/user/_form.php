<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\jui\DatePicker;
use yii\helpers\ArrayHelper;


$ispostave= \app\models\Ispostava::find()->all();
$ispostaveList = ArrayHelper::map($ispostave, 'id_ispostava', 'id_ispostava');
/* @var $this yii\web\View */
/* @var $model app\models\User */
/* @var $form yii\widgets\ActiveForm */


?>

<div class="user-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'ime')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'prezime')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'oib')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'dat_rod')->textInput() ?>

    <?=
    $form->field($model, 'spol')
        ->dropDownList(
           ["m"=>"Muški","ž"=>"Ženski"]         // Flat array ('id'=>'label')
            // options
        );
    ?>

    <?= $form->field($model, 'adresa_stanovanja')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'mjesto_stanovanja')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'broj_tel')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'mob')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'napomena')->textInput(['maxlength' => true]) ?>

    <?=
    $form->field($model, 'vrsta')
        ->dropDownList(
            [1=>"Administrator",0=>"Običan"]           // Flat array ('id'=>'label')
              // options
        );
    ?>

    <?=
    $form->field($model, 'id_ispostava')
        ->dropDownList(
            $ispostaveList        // Flat array ('id'=>'label')
        // options
        );
    ?>



    <?= $form->field($model, 'username')->textInput(['maxlength' => true]) ?>

    <?php if($model->isNewRecord): ?>

        <?= $form->field($model, 'password_hash')->textInput(['maxlength' => true]) ?>
    <?php endif;if(!$model->isNewRecord): ?>
    <?= $form->field($model, 'broj_sati')->textInput() ?>
    <?php endif;?>
    


    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
