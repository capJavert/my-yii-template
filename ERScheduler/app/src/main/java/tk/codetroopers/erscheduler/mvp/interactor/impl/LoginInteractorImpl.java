package tk.codetroopers.erscheduler.mvp.interactor.impl;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.helpers.ErrorExtractor;
import tk.codetroopers.erscheduler.listeners.ExtraListener;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.models.User;
import tk.codetroopers.erscheduler.mvp.interactor.LoginInteractor;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;


public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final ExtraListener listener) {

        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<BaseReponse> call = apiService.login(username, password);

        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
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
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                Log.v("LOGIN: ", "FAILURE");
                listener.onFailure();
            }
        });
    }

    // User data is saved to application data (not Shared Preferences yet)
    private void saveUserData(String username, String password, String token) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        SchedulerApp.setLoggedUser(user);
    }
}
