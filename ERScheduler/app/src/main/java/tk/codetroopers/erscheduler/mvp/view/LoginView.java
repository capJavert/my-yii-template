package tk.codetroopers.erscheduler.mvp.view;

public interface LoginView {

    void showNoCredentials();

    void showCredentialsError();

    void showBadCredentials();

    void showError(String error);

    void navigateToMain();
}
