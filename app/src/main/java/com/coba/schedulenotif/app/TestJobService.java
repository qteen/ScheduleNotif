package com.coba.schedulenotif.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by wbs on 1/29/2016.
 */
public class TestJobService extends Service {
    private static final String TAG = "SyncService";
    public static final int TEST_NOTIF_ID = 152;


    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        // We don't do any real 'work' in this sample app. All we'll
        // do is track which jobs have landed on our service, and
        // update the UI accordingly.
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Random random = new Random(100);
        String contentText = "Ada Notif Baru "+ random.nextDouble();
        // http://developer.android.com/reference/android/app/Notification.Builder.html
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New Notification "+random.nextInt())
                .setContentText(contentText)
                .setTicker(contentText)
                .setSmallIcon(R.drawable.ic_xxx)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_merge, "Merge", pIntent).build();
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(TEST_NOTIF_ID, noti);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
