<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Generiraj raspored';
//$this->params['breadcrumbs'][] = $this->title;
?>
<div class="users-timovi-index">

    <div class="jumbotron">
        <h1>Geneirajte raspored</h1>
        <?php $form = ActiveForm::begin(); ?>

        <div class="col-md-12 form-group">
        <?= $form->field($model, 'broj_dana')->textInput() ?>
        </div>

        <div class="form-group">
        <?= Html::submitButton('Generiraj', ['class' => 'btn btn-lg btn-success', 'name' => 'Generiraj']) ?>
    </div>


        <?php ActiveForm::end(); ?>




    </div>
</div>
