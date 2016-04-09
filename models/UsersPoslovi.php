<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "users_poslovi".
 *
 * @property integer $id_user
 * @property integer $id_posao
 * @property integer $prioritet
 *
 * @property Poslovi $idPosao
 * @property User $idUser
 */
class UsersPoslovi extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'users_poslovi';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_user', 'id_posao'], 'required'],
            [['id_user', 'id_posao', 'prioritet'], 'integer'],
            [['id_posao'], 'exist', 'skipOnError' => true, 'targetClass' => Poslovi::className(), 'targetAttribute' => ['id_posao' => 'id_posao']],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['id_user' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id_user' => 'Id User',
            'id_posao' => 'Id Posao',
            'prioritet' => 'Prioritet',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdPosao()
    {
        return $this->hasOne(Poslovi::className(), ['id_posao' => 'id_posao']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdUser()
    {
        return $this->hasOne(User::className(), ['id' => 'id_user']);
    }
}
