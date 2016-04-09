<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "timovi".
 *
 * @property integer $id_tim
 * @property integer $vrsta
 * @property integer $id_ispostava
 *
 * @property Ispostava $idIspostava
 * @property UsersTimovi[] $usersTimovis
 */
class Timovi extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'timovi';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['vrsta', 'id_ispostava'], 'integer'],
            [['id_ispostava'], 'required'],
            [['id_ispostava'], 'exist', 'skipOnError' => true, 'targetClass' => Ispostava::className(), 'targetAttribute' => ['id_ispostava' => 'id_ispostava']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_tim' => 'Id Tim',
            'vrsta' => 'Vrsta',
            'id_ispostava' => 'Id Ispostava',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdIspostava()
    {
        return $this->hasOne(Ispostava::className(), ['id_ispostava' => 'id_ispostava']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsersTimovis()
    {
        return $this->hasMany(UsersTimovi::className(), ['id_tim' => 'id_tim']);
    }

    public function getNextJob() {
        if($this->vrsta)
            $jobs = 'DVMT';
        else
            $jobs = 'VT';

        $UT = UsersTimovi::find()->where('id_tim='.$this->id_tim)->all();

        foreach($UT as $ut) {
            if($ut->posao=='D') str_replace('D', '', $jobs);
            if($ut->posao=='V') str_replace('V', '', $jobs);
            if($ut->posao=='M') str_replace('M', '', $jobs);
            if($ut->posao=='T') str_replace('T', '', $jobs);
        }

        return str_split($jobs)[0];
    }
}
