package com.coba.schedulenotif.app;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by wbs on 1/29/2016.
 */
public class NotificationReceiverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancel(TestJobService.TEST_NOTIF_ID);
                finish();
            }
        }, 2000);
    }
}
