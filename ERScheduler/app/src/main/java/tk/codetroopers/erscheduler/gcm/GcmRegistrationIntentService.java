package tk.codetroopers.erscheduler.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

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
            String token = instanceID.getToken("136279074754", GoogleCloudMessaging.INSTANCE_ID_SCOPE);

            Log.i(TAG, "GCM Registration Token: " + token);
            sharedPreferences.edit().putString(GCM_TOKEN, token).apply();

            sendRegistrationTokenToServer(token);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    private void sendRegistrationTokenToServer(String token) {
        // TODO: Send gcm token to server
    }
}
