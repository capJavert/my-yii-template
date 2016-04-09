<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\UsersTimovi */

$this->title = 'Kreiraj timove';
$this->params['breadcrumbs'][] = ['label' => 'Timovi', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="users-timovi-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
