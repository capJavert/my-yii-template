<?php

/* @var $this yii\web\View */

$this->title = 'Raspored';

if(isset($_GET['brojDana']))
    $brojDana = $_GET['brojDana'];
else
    $brojDana = 0;
?>
<div class="site-index">
    <div class="container" id="scheduler">
        <div class="col-md-12">
            <h2>Raspored, kreiran datuma 09.09.2016</h2>
        </div>

        <?php if($brojDana) { ?>
        <?php for($i=1;$i<=$brojDana;$i++) {?>
        <div data-id="<?= 'box'.($i-1) ?>" class="col-md-2 box-container box-small">
            <a href="./scheduledetail/?day=<?= $i ?>"><div class="day">
                Dan <?= $i ?>
            </div></a>
        </div>
        <?php } ?>
        <?php } else echo '<div class="col-md-12"><h3>Nije definiran vremenski raspon..</h3></div>' ?>
    </div>
</div>
