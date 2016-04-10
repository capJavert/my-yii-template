<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Generiraj raspored';
//$this->params['breadcrumbs'][] = $this->title;
?>
<div class="users-timovi-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Kreiraj novu smjenu', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            [
                'attribute' => 'id_user',
                'format' => 'raw',
                'value' => function ($model) {
                    return \app\models\User::findOne($model->id_user)->ime.' '.app\models\User::findOne($model->id_user)->prezime;
                },
            ],
            [
                'attribute' => 'id_tim',
                'format' => 'raw',
                'value' => function ($model) {
                    return 'Tim #'.$model->id_tim;
                },
            ],
            [
                'attribute' => 'dan',
                'format' => 'raw',
                'value' => function ($model) {
                    return 'Dan '.$model->dan;
                },
            ],
            [
                'attribute' => 'dan',
                'format' => 'raw',
                'value' => function ($model) {
                    return ($model->smjena ? 'Dnevna':'Noćna');
                },
            ],
            // 'posao',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
