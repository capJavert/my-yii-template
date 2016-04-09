<?php

use app\models\User;
use app\models\Timovi;
use Yii;
use app\models\UsersTimovi;
use yii\data\ActiveDataProvider;
use yii\helpers\ArrayHelper;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;


/* @var $this yii\web\View */
/* @var $model app\models\UsersTimovi */
/* @var $form yii\widgets\ActiveForm */

$users= User::find()->all();
$listUsers= ArrayHelper::map($users,'id','naziv');
$timovi = Timovi::find()->all();
$listTimovi = ArrayHelper::map($timovi, 'id_tim', 'id_tim');

?>

<div class="users-timovi-form">

    <?php $form = ActiveForm::begin(); ?>

    <?=  Html::dropDownList('ispostava', Yii::$app->request->get('i'), $listIspostave, [
        'prompt' => 'Ispostave',
        'class' => 'form-control',
    ]);
    ?>

    <?= $form->field($model, 'id_tim')->textInput() ?>

    <?= $form->field($model, 'dan')->textInput() ?>

    <?= $form->field($model, 'smjena')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
