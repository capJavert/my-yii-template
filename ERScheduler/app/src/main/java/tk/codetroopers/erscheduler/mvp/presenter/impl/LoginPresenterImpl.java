package tk.codetroopers.erscheduler.mvp.presenter.impl;

import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.listeners.ExtraListener;
import tk.codetroopers.erscheduler.listeners.Listener;
import tk.codetroopers.erscheduler.mvp.interactor.LoginInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.impl.LoginInteractorImpl;
import tk.codetroopers.erscheduler.mvp.presenter.LoginPresenter;
import tk.codetroopers.erscheduler.mvp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void userLogin(String username, String password) {
        if(username.isEmpty() || password.isEmpty())
            view.showNoCredentials();
        else if(username.contains(" ") || password.contains(" "))
            view.showCredentialsError();
        else {
            interactor.login(username, password, loginListener);
        }
    }

    private void getUserData(){
        interactor.getUserData(userInfoListener, SchedulerApp.getLoggedUser());
    }

    private ExtraListener loginListener = new ExtraListener() {
        @Override
        public void onSuccess() {
            SchedulerApp.getInstance().setAppState(AppStateEnum.LoggingIn);
            getUserData();
        }

        @Override
        public void onFailure() {
            SchedulerApp.getInstance().setAppState(AppStateEnum.NotSignedIn);
            view.showBadCredentials();
        }

        @Override
        public void onFailure(String error) {
            SchedulerApp.getInstance().setAppState(AppStateEnum.NotSignedIn);
            view.showError(error);
        }
    };

    private Listener userInfoListener = new Listener() {
        @Override
        public void onSuccess() {
            view.navigateToMain();
        }

        @Override
        public void onFailure() {
            view.showBadCredentials();
        }
    };
}
