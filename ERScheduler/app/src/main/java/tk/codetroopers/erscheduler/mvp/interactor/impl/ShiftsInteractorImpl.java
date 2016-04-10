package tk.codetroopers.erscheduler.mvp.interactor.impl;

import android.util.Log;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.listeners.ShiftsListener;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.Shifts;
import tk.codetroopers.erscheduler.models.User;
import tk.codetroopers.erscheduler.mvp.interactor.ShiftsInteractor;
import tk.codetroopers.erscheduler.mvp.interactor.TemplateInteractor;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;

public class ShiftsInteractorImpl implements ShiftsInteractor {

    private final static String MODEL_NAME_SHIFTS = "Timovi";

    @Override
    public void getNewShifts(final ShiftsListener listener) {
        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<Shifts> call = apiService.getSchedule(MODEL_NAME_SHIFTS, SchedulerApp.getLoggedUser().getToken());
        call.enqueue(new Callback<Shifts>() {
            @Override
            public void onResponse(Call<Shifts> call, Response<Shifts> response) {
                if (response != null && response.body() != null) {
                    Shift.clearAllShifts();
                    if (response.body().getShifts() != null) {
                        Log.v("SHIFTS: ", "SUCCESS");
                        SchedulerApp.getLoggedUser().setLastRefresh(new Date());
                        Shift.saveAllShifts(response.body().getShifts());
                        listener.onSuccess(response.body().getShifts());
                    } else {
                        listener.onFailure();
                    }
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Shifts> call, Throwable t) {
                Log.v("SHIFTS: ", "FAILURE");
                listener.onFailure();
            }
        });
    }
}
