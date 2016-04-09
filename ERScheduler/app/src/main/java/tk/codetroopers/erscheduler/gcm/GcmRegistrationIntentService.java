package tk.codetroopers.erscheduler.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.SchedulerApp;
import tk.codetroopers.erscheduler.helpers.ErrorExtractor;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.network.ApiModule;
import tk.codetroopers.erscheduler.network.ApiService;

public class GcmRegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    public static final String GCM_TOKEN = "gcmToken";

    public GcmRegistrationIntentService() {
        super("GcmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(SchedulerApp.getInstance().getContexter().getStringValue(R.string.gcm_sender_id), GoogleCloudMessaging.INSTANCE_ID_SCOPE);

            Log.i(TAG, "GCM Registration Token: " + token);
            sharedPreferences.edit().putString(GCM_TOKEN, token).apply();

            sendRegistrationTokenToServer(token);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    private void sendRegistrationTokenToServer(final String token) {
        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<BaseReponse> call = apiService.sendToken(token);

        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getToken() != null) {
                        Log.v("LOGIN: ", response.body().getToken());
                        //listener.onSuccess();
                    } else if (response.body().getErrors() != null) {
                        Log.v("LOGIN: ", "FAILURE");
                        String error = ErrorExtractor.getErrors(response.body().getErrors());
                        //listener.onFailure(error);
                    } else {
                        Log.v("LOGIN: ", response.body().getErrors().getPasswordErrors().get(0));
                        //listener.onFailure();
                    }
                } else {
                    //listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                Log.v("LOGIN: ", "FAILURE");
                //listener.onFailure();
            }
        });
    }
}
