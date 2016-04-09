<?php

/* @var $this yii\web\View */

$this->title = 'Raspored';
?>
<div class="site-index">
    <div class="container" id="scheduler">
        <div class="col-md-12">
            <h2>Raspored, kreiran datuma 09.09.2016</h2>
        </div>

        <?php for($i=1;$i<28;$i++) {?>
        <div data-id="<?= 'box'.($i-1) ?>" class="col-md-2 box-container box-small">
            <a href="/site/scheduledetail/?day=<?= $i ?>"><div class="day">
                Dan <?= $i ?>
            </div></a>
        </div>
        <?php } ?>
    </div>
</div>
