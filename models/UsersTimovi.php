<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "users_timovi".
 *
 * @property integer $id
 * @property integer $id_user
 * @property integer $id_tim
 * @property integer $dan
 *
 * @property Timovi $idTim
 * @property User $idUser
 */
class UsersTimovi extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'users_timovi';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_user', 'id_tim'], 'required'],
            [['id_user', 'id_tim', 'smjena', 'dan'], 'integer'],
            [['posao'], 'string', 'max' => 1],
            [['id_tim'], 'exist', 'skipOnError' => true, 'targetClass' => Timovi::className(), 'targetAttribute' => ['id_tim' => 'id_tim']],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['id_user' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'id_user' => 'Korisnik',
            'id_tim' => 'Tim',
            'dan' => 'Dan',
            'smjena'=>'Smjena'
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdTim()
    {
        return $this->hasOne(Timovi::className(), ['id_tim' => 'id_tim']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdUser()
    {
        return $this->hasOne(User::className(), ['id' => 'id_user']);
    }
}
