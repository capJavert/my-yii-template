<?php

namespace app\models;

use Yii;

use yii\behaviors\TimestampBehavior;
use yii\db\ActiveRecord;
use yii\web\IdentityInterface;

/**
 * This is the model class for table "user".
 *
 * @property integer $id
 * @property string $ime
 * @property string $prezime
 * @property string $oib
 * @property string $dat_rod
 * @property string $spol
 * @property string $adresa_stanovanja
 * @property string $mjesto_stanovanja
 * @property string $broj_tel
 * @property string $mob
 * @property string $napomena
 * @property integer $vrsta
 * @property integer $id_ispostava
 * @property integer $broj_sati
 * @property string $username
 * @property string $auth_key
 * @property string $password_hash
 * @property string $password_reset_token
 * @property integer $status
 * @property integer $updated_at
 * @property integer $created_at
 *
 * @property Ispostava $idIspostava
 * @property UsersPoslovi[] $usersPoslovis
 * @property Poslovi[] $idPosaos
 * @property UsersTimovi[] $usersTimovis
 */
class User extends ActiveRecord implements IdentityInterface
{
    const STATUS_DELETED = 0;
    const STATUS_ACTIVE = 10;

    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'user';
    }

    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            TimestampBehavior::className(),
        ];
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['ime', 'prezime', 'oib', 'dat_rod'], 'required'],
            [['dat_rod'], 'safe'],
            [['vrsta', 'id_ispostava', 'broj_sati', 'status', 'updated_at', 'created_at'], 'integer'],
            [['ime', 'prezime', 'oib', 'adresa_stanovanja', 'mjesto_stanovanja', 'broj_tel', 'mob', 'username'], 'string', 'max' => 45],
            [['spol'], 'string', 'max' => 5],
            [['dostupan'], 'string', 'max' => 100],
            [['napomena'], 'string', 'max' => 300],
            [['auth_key'], 'string', 'max' => 32],
            [['password_hash', 'password_reset_token'], 'string', 'max' => 255],
            [['id_ispostava'], 'exist', 'skipOnError' => true, 'targetClass' => Ispostava::className(), 'targetAttribute' => ['id_ispostava' => 'id_ispostava']],
            ['status', 'default', 'value' => self::STATUS_ACTIVE],
            ['status', 'in', 'range' => [self::STATUS_ACTIVE, self::STATUS_DELETED]],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'ime' => 'Ime',
            'prezime' => 'Prezime',
            'oib' => 'Oib',
            'dat_rod' => 'Dat Rod',
            'spol' => 'Spol',
            'adresa_stanovanja' => 'Adresa Stanovanja',
            'mjesto_stanovanja' => 'Mjesto Stanovanja',
            'broj_tel' => 'Broj Tel',
            'mob' => 'Mob',
            'napomena' => 'Napomena',
            'vrsta' => 'Vrsta',
            'id_ispostava' => 'Id Ispostava',
            'broj_sati' => 'Broj Sati',
            'username' => 'Username',
            'auth_key' => 'Auth Key',
            'password_hash' => 'Password',
            'password_reset_token' => 'Password Reset Token',
            'status' => 'Status',
            'updated_at' => 'Updated At',
            'created_at' => 'Created Ad',
            'dostupan' => 'Dostupnost',
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
    public function getUsersPoslovis()
    {
        return $this->hasMany(UsersPoslovi::className(), ['id_user' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getIdPosaos()
    {
        return $this->hasMany(Poslovi::className(), ['id_posao' => 'id_posao'])->viaTable('users_poslovi', ['id_user' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsersTimovis()
    {
        return $this->hasMany(UsersTimovi::className(), ['id_user' => 'id']);
    }

    /**
     * @inheritdoc
     */
    public static function findIdentity($id)
    {
        return static::findOne(['id' => $id, 'status' => self::STATUS_ACTIVE]);
    }

    /**
     * @inheritdoc
     */
    public static function findIdentityByAccessToken($token, $type = null)
    {
        return static::findOne(['auth_key' => $token, 'status' => self::STATUS_ACTIVE]);
    }

    /**
     * Finds user by username
     *
     * @param string $username
     * @return static|null
     */
    public static function findByUsername($username)
    {
        return static::findOne(['username' => $username, 'status' => self::STATUS_ACTIVE]);
    }

    /**
     * Finds user by username
     *
     * @param string $username
     * @return static|null
     */
    public static function findByToken($username)
    {
        return User::findOne(['auth_key' => $username, 'status' => self::STATUS_ACTIVE]);
    }

    /**
     * Finds user by password reset token
     *
     * @param string $token password reset token
     * @return static|null
     */
    public static function findByPasswordResetToken($token)
    {
        if (!static::isPasswordResetTokenValid($token)) {
            return null;
        }

        return static::findOne([
            'password_reset_token' => $token,
            'status' => self::STATUS_ACTIVE,
        ]);
    }

    /**
     * Finds out if password reset token is valid
     *
     * @param string $token password reset token
     * @return boolean
     */
    public static function isPasswordResetTokenValid($token)
    {
        if (empty($token)) {
            return false;
        }

        $timestamp = (int) substr($token, strrpos($token, '_') + 1);
        $expire = Yii::$app->params['user.passwordResetTokenExpire'];
        return $timestamp + $expire >= time();
    }

    /**
     * @inheritdoc
     */
    public function getId()
    {
        return $this->getPrimaryKey();
    }

    /**
     * @inheritdoc
     */
    public function getAuthKey()
    {
        return $this->auth_key;
    }

    /**
     * @inheritdoc
     */
    public function validateAuthKey($authKey)
    {
        return $this->getAuthKey() === $authKey;
    }

    /**
     * Validates password
     *
     * @param string $password password to validate
     * @return boolean if password provided is valid for current user
     */
    public function validatePassword($password)
    {
        return Yii::$app->security->validatePassword($password, $this->password_hash);
    }

    /**
     * Generates password hash from password and sets it to the model
     *
     * @param string $password
     */
    public function setPassword($password)
    {
        $this->password_hash = Yii::$app->security->generatePasswordHash($password);
    }

    /**
     * Generates "remember me" authentication key
     */
    public function generateAuthKey()
    {
        $this->auth_key = Yii::$app->security->generateRandomString();
    }

    /**
     * Generates new password reset token
     */
    public function generatePasswordResetToken()
    {
        $this->password_reset_token = Yii::$app->security->generateRandomString() . '_' . time();
    }

    /**
     * Removes password reset token
     */
    public function removePasswordResetToken()
    {
        $this->password_reset_token = null;
    }
}
