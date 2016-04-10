package tk.codetroopers.erscheduler.mvp.interactor.impl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.listeners.Listener;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.mvp.interactor.MainInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;

public class MainInteractorImpl implements MainInteractor {

    @Override
    public void logout(final Listener listener) {
        ApiService apiService = ApiModule.createService(ApiService.class,
                SchedulerApp.getLoggedUser().getToken());

        Call<BaseReponse> call = apiService.logout(SchedulerApp.getLoggedUser().getToken());
        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                if (response != null && response.body() != null && response.body().getSuccess() != null) {
                    listener.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
