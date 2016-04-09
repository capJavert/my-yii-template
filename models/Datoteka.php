<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "datoteka".
 *
 * @property integer $id
 * @property integer $broj_tjedana
 * @property integer $broj_ispostava
 */
class Datoteka extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'datoteka';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['broj_tjedana', 'broj_ispostava'], 'required'],
            [['broj_tjedana', 'broj_ispostava'], 'integer'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'broj_tjedana' => 'Broj Tjedana',
            'broj_ispostava' => 'Broj Ispostava',
        ];
    }
}
