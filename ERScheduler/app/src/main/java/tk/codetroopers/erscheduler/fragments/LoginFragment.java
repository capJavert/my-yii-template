package tk.codetroopers.erscheduler.fragments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.enums.ActivityEnum;
import tk.codetroopers.erscheduler.enums.AppStateEnum;
import tk.codetroopers.erscheduler.mvp.presenter.LoginPresenter;
import tk.codetroopers.erscheduler.mvp.presenter.impl.LoginPresenterImpl;
import tk.codetroopers.erscheduler.mvp.view.ActivityView;
import tk.codetroopers.erscheduler.mvp.view.LoginView;

public class LoginFragment extends BaseFragment implements LoginView {

    EditText ETusername;
    EditText ETpassword;
    TextView TVMessage;
    Button btnLogin;

    private LoginPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.login_fragment, container, false);

        presenter = new LoginPresenterImpl(this);

        ETusername = (EditText) viewInflater.findViewById(R.id.username);
        ETpassword = (EditText) viewInflater.findViewById(R.id.password);
        btnLogin = (Button) viewInflater.findViewById(R.id.btnLogin);

        TVMessage = (TextView) viewInflater.findViewById(R.id.TVMessage);
        TVMessage.setText("");

        ((ActivityView) getContext()).showTitle(getContext().getString(R.string.toolbar_app_title));
        SchedulerApp.getInstance().setAppState(AppStateEnum.NotSignedIn);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < android.os.Build.VERSION_CODES.LOLLIPOP) {
            btnLogin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        }
        //TEST
        ETusername.setText("admin");
        ETpassword.setText("admin");

        setupParent(viewInflater);
        initilizeEvents();
        return viewInflater;
    }

    @Override
    public void showNoCredentials() {
        showMessage(getContext().getString(R.string.empty_login));
    }

    @Override
    public void showCredentialsError() {
        showMessage(getContext().getString(R.string.no_spaces_allowed));
    }

    @Override
    public void showBadCredentials() {
        showMessage(getContext().getString(R.string.login_error));
    }

    @Override
    public void showError(String error) {
        showMessage(error);
    }

    @Override
    public void navigateToMain() {
        showMessage("");
        ((ActivityView) getContext()).showActivity(ActivityEnum.MainActivity);
    }

    @Override
    public void showMessage(String message) {
        TVMessage.setText(message);
    }

    private void initilizeEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMessage(getString(R.string.login_in_progress));
                presenter.userLogin(ETusername.getText().toString(),
                        ETpassword.getText().toString());
            }
        });
    }
}