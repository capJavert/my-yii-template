<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ispostava".
 *
 * @property integer $id_ispostava
 * @property string $lokacija
 * @property integer $rang
 *
 * @property Timovi[] $timovis
 * @property User[] $users
 */
class Ispostava extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'ispostava';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['lokacija'], 'required'],
            [['rang'], 'integer'],
            [['lokacija'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_ispostava' => 'Id Ispostava',
            'lokacija' => 'Lokacija',
            'rang' => 'Rang',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTimovis()
    {
        return $this->hasMany(Timovi::className(), ['id_ispostava' => 'id_ispostava']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsers()
    {
        return $this->hasMany(User::className(), ['id_ispostava' => 'id_ispostava']);
    }

    public function defaultScope() {
        return [
          'criteria'=>'id_ispostava>1'
        ];
    }
}
