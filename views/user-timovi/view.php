<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\UsersTimovi */

$this->title = $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Users Timovis', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="users-timovi-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id, 'id_user' => $model->id_user, 'id_tim' => $model->id_tim], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id, 'id_user' => $model->id_user, 'id_tim' => $model->id_tim], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'id',
            'id_user',
            'id_tim',
            'dan',
            'smjena',
        ],
    ]) ?>

</div>
