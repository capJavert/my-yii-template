<?php

/* @var $this yii\web\View */

use yii\helpers\Html;
use yii\grid\GridView;
use app\models\User;
use app\models\UsersTimovi;



$this->title = 'Raspored';
?>
<div class="site-index">
    <div class="container" id="scheduler">
        <div class="col-md-12 title-bar">
            <h2>Raspored, dan <?= $dayId ?></h2>
        </div>
        <div class="filters">
            <div class="col-md-4 form-group">
                <?php
                echo Html::dropDownList('ispostava', Yii::$app->request->get('i'), $listIspostave, [
                    'prompt' => 'Ispostave',
                    'class' => 'form-control',
                ]);
                ?>
            </div>
            <div class="col-md-4 form-group">
                <?php
                echo Html::dropDownList('tim', Yii::$app->request->get('t'), $listTimovi, [
                    'prompt' => 'Timovi',
                    'class' => 'form-control',
                ]);
                ?>
            </div>
            <div class="col-md-4 form-group">
                <?php
                echo Html::dropDownList('smjena', Yii::$app->request->get('s'), $listSmjene, [
                    'prompt' => 'Smjena',
                    'class' => 'form-control',
                ]);
                ?>
            </div>
        </div>

        <section class="col-md-9 box-container box-large">
            <div class="day-large">
                <h3>Dan <?= $dayId ?></h3>
                <?= GridView::widget([
                    'dataProvider' => $dataProvider,
                    'columns' => [['class' => 'yii\grid\SerialColumn'],
                            'ispostava',
                        'tim',
                        'user',
                        'smjena',
                        'posao',




                    ],
                ]);
              ?>

            </div>
        </section>
        <aside class="col-md-3 info-sidebar">
            <ul>
                <li>Broj djelatnika: <?  ?></li>
                <li>Na godišnjem: <? ?></li>
                <li>Na bolovanju: 8</li>
            </ul>
        </aside>
    </div>
</div>