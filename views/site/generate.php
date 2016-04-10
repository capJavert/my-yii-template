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
        <form class="form-horizontal" action="/site/schedule/" method="get">
            <fieldset>

                <!-- Form Name -->


                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Broj dana">Broj dana</label>
                    <div class="col-md-4">
                        <input id="Broj dana" name="Broj dana" type="text" placeholder="dani" class="form-control input-md">

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <button id="singlebutton" name="singlebutton" class="btn btn-primary">Generiraj</button>
                    </div>
                </div>

            </fieldset>
        </form>



    </div>
</div>
