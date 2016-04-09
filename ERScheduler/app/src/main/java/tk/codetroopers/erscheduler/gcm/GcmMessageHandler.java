package tk.codetroopers.erscheduler.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;

import tk.codetroopers.erscheduler.R;
import tk.codetroopers.erscheduler.activities.MainActivity;
import tk.codetroopers.erscheduler.models.GcmMessage;

public class GcmMessageHandler extends GcmListenerService {

    private final String TAG = "GcmMessageHandler";
    private final String ACTION = "action";

    @Override
    public void onMessageReceived(String from, Bundle data) {

        String json = data.getString("message");
        GcmMessage gcmMessage = getObjectFromJson(json);

        Log.i("GCM message: ", gcmMessage.getTitle());
        Log.i("GCM sender:  ", gcmMessage.getMessage());

        showNotification(gcmMessage.getTitle(), gcmMessage.getMessage(), gcmMessage.getAction());
    }

    private void showNotification(String title, String message, String action) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(ACTION, action);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_plus_signin_btn_icon_light)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private GcmMessage getObjectFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GcmMessage.class);
    }
}
