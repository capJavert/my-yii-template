<?php

namespace app\models;

use Yii;
use yii\base\Model;

/**
 * ContactForm is the model behind the contact form.
 */
class RasporedForm extends Model
{
    public $broj_dana;
    public $ispostava;
    public $tim;
    public $user;
    public $smjena;
    public $posao;

    /**
     * RasporedForm constructor.
     *
     * @param $ispostava
     * @param $tim
     * @param $user
     */
    public function __construct($ispostava, $tim, $user,$smjena,$posao)
    {
        $this->ispostava = $ispostava;
        $this->tim = $tim;
        $this->user = $user;
        $this->smjena = $smjena;
        $this->posao= $posao;
    }

   

    /**
     * @return array the validation rules.
     */
    public function rules()
    {
        return [
            // name, email, subject and body are required
         //   [['broj_dana'], 'required'],
            // email has to be a valid email address

            // verifyCode needs to be entered correctly

        ];
    }

    /**
     * @return array customized attribute labels
     */
    public function attributeLabels()
    {
        return [
            'broj_dana' => 'Broj dana',
        ];
    }

  

    }
