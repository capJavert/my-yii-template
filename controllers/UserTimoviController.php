<?php

namespace app\controllers;

use app\models\User;
use Yii;
use app\models\UsersTimovi;
use yii\data\ActiveDataProvider;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * UserTimoviController implements the CRUD actions for UsersTimovi model.
 */
class UserTimoviController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],
        ];
    }

    /**
     * Lists all UsersTimovi models.
     * @return mixed
     */
    public function actionIndex()
    {
        $dataProvider = new ActiveDataProvider([
            'query' => UsersTimovi::find(),
        ]);

        return $this->render('index', [
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single UsersTimovi model.
     * @param integer $id
     * @param integer $id_user
     * @param integer $id_tim
     * @return mixed
     */
    public function actionView($id, $id_user, $id_tim)
    {
        return $this->render('view', [
            'model' => $this->findModel($id, $id_user, $id_tim),
        ]);
    }

    /**
     * Creates a new UsersTimovi model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $users= User::find()->all();
        $model = new UsersTimovi();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id, 'id_user' => $model->id_user, 'id_tim' => $model->id_tim]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing UsersTimovi model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @param integer $id_user
     * @param integer $id_tim
     * @return mixed
     */
    public function actionUpdate($id, $id_user, $id_tim)
    {
        $model = $this->findModel($id, $id_user, $id_tim);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id, 'id_user' => $model->id_user, 'id_tim' => $model->id_tim]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing UsersTimovi model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @param integer $id_user
     * @param integer $id_tim
     * @return mixed
     */
    public function actionDelete($id, $id_user, $id_tim)
    {
        $this->findModel($id, $id_user, $id_tim)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the UsersTimovi model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @param integer $id_user
     * @param integer $id_tim
     * @return UsersTimovi the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id, $id_user, $id_tim)
    {
        if (($model = UsersTimovi::findOne(['id' => $id, 'id_user' => $id_user, 'id_tim' => $id_tim])) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
