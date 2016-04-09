<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "poslovi".
 *
 * @property integer $id_posao
 * @property string $naziv
 *
 * @property UsersPoslovi[] $usersPoslovis
 * @property User[] $idUsers
 */
class Poslovi extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'poslovi';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['naziv'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_posao' => 'Id Posao',
            'naziv' => 'Naziv',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsersPoslovis()
    {
        return $this->hasMany(UsersPoslovi::className(), ['id_posao' => 'id_posao']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdUsers()
    {
        return $this->hasMany(User::className(), ['id_user' => 'id_user'])->viaTable('users_poslovi', ['id_posao' => 'id_posao']);
    }
}
