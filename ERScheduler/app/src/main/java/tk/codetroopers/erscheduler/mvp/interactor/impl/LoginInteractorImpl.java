package tk.codetroopers.erscheduler.mvp.interactor.impl;

import android.util.Log;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.helpers.ErrorExtractor;
import tk.codetroopers.erscheduler.listeners.ExtraListener;
import tk.codetroopers.erscheduler.listeners.Listener;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.models.User;
import tk.codetroopers.erscheduler.mvp.interactor.LoginInteractor;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;


public class LoginInteractorImpl implements LoginInteractor {

    private final static String MODEL_NAME_USER_INFO = "User";

    @Override
    public void login(final String username, final String password, final ExtraListener listener) {

        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<BaseReponse> call = apiService.login(username, password);

        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getToken() != null) {
                        Log.v("LOGIN: ", response.body().getToken());
                        saveUserData(username, password, response.body().getToken());
                        listener.onSuccess();
                    } else if (response.body().getErrors() != null) {
                        Log.v("LOGIN: ", "FAILURE");
                        String error = ErrorExtractor.getErrors(response.body().getErrors());
                        listener.onFailure(error);
                    } else {
                        Log.v("LOGIN: ", response.body().getErrors().getPasswordErrors().get(0));
                        listener.onFailure();
                    }
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                Log.v("LOGIN: ", "FAILURE");
                listener.onFailure();
            }
        });
    }

    @Override
    public void getUserData(final Listener listener, User user) {
        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<User> call = apiService.getUser(MODEL_NAME_USER_INFO, user.getToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getName() != null) {
                        Log.v("LOGIN: ", response.body().getName());
                        saveUserInfo(response.body());
                        listener.onSuccess();
                    } else {
                        listener.onFailure();
                    }
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("LOGIN: ", "FAILURE");
                listener.onFailure();
            }
        });
    }

    // DbUser data is saved to application data (not Shared Preferences yet)
    private void saveUserData(String username, String password, String token) {
        User.clearUsers();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        SchedulerApp.setLoggedUser(user);
    }

    private void saveUserInfo(User user) {
        User.clearUsers();
        User newUser = new User(user.getName(), user.getSurname(), user.getUsername(), null, user.getToken(), user.getOib(),
                user.getBirthDate(), user.getAddress(), user.getPlace(), user.getPhoneNumber(), user.getMobileNumber(),
                user.getRemark(), user.getCentral(), user.getNumberOfHours(), new Date());

        SchedulerApp.setLoggedUser(newUser);
    }
}
