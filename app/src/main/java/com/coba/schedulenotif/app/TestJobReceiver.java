package com.coba.schedulenotif.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by wbs on 1/29/2016.
 */
public class TestJobReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, TestJobService.class));
    }
}
