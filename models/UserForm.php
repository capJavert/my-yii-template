<?php

namespace app\models;

use Yii;
use yii\base\Model;
use app\models\User;

/**
 * LoginForm is the model behind the login form.
 */
class Userform extends Model
{
    public $ime;
    public $prezime;
    public $lokacija;
    public $password;
    public $token;
    public $oib;
    public $dat_rod;
    public $adresa_stanovanja;
    public $mjesto_stanovanja;
    public $broj_tel;
    public $mob;
    public $napomena;
    public $ispostava;
    public $broj_sati;
    public $jobs = array();

    /**
     * Userform constructor.
     *
     * @param $ime
     * @param $prezime
     * @param $lokacija
     * @param $password
     * @param $token
     * @param $oib
     * @param $dat_rod
     * @param $adresa_stanovanja
     * @param $mjesto_stanovanja
     * @param $broj_tel
     * @param $mob
     * @param $napomena
     * @param $ispostava
     * @param $broj_sati
     * @param $jobs
     */
    public function __construct(
        $ime,
        $prezime,
        $lokacija,
        $token,
        $oib,
        $dat_rod,
        $adresa_stanovanja,
        $mjesto_stanovanja,
        $broj_tel,
        $mob,
        $napomena,
        $ispostava,
        $broj_sati

    ) {
        $this->ime = $ime;
        $this->prezime = $prezime;
        $this->lokacija = $lokacija;
        $this->token = $token;
        $this->oib = $oib;
        $this->dat_rod = $dat_rod;
        $this->adresa_stanovanja = $adresa_stanovanja;
        $this->mjesto_stanovanja = $mjesto_stanovanja;
        $this->broj_tel = $broj_tel;
        $this->mob = $mob;
        $this->napomena = $napomena;
        $this->ispostava = $ispostava;
        $this->broj_sati = $broj_sati;
    //    $this->jobs = $jobs;
    }



    //  private $_user = false;


    /**
     * @return array the validation rules.
     */
    public function rules()
    {
        return [

        ];
    }

    /**
     * Validates the password.
     * This method serves as the inline validation for password.
     *
     * @param string $attribute the attribute currently being validated
     * @param array $params the additional name-value pairs given in the rule
     */
    public function validatePassword($attribute, $params)
    {
        if (!$this->hasErrors()) {
            $user = $this->getUser();

            if (!$user || !$user->validatePassword($this->password)) {
                $this->addError($attribute, 'Incorrect username or password.');
            }
        }
    }

    /**
     * Logs in a user using the provided username and password.
     * @return boolean whether the user is logged in successfully
     */
    public function login()
    {
        if ($this->validate()) {
            return Yii::$app->user->login($this->getUser(), $this->rememberMe ? 3600*24*30 : 0);
        }

        return false;
    }

    /**
     * Finds user by [[username]]
     *
     * @return User|null
     */
    public function getUser()
    {
        if ($this->_user === false) {
            $this->_user = User::findByUsername($this->username);
        }

        return $this->_user;
    }
}
