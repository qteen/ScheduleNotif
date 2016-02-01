package com.coba.schedulenotif.app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends Activity {
    public static final int MSG_UNCOLOUR_START = 0;
    public static final int MSG_UNCOLOUR_STOP = 1;
    public static final int MSG_SERVICE_OBJ = 2;
    private AlarmManager alarmManager;
    private Intent testJobReceiver;
    private PendingIntent pendingJobIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        defaultColor = res.getColor(R.color.none_received);
        startJobColor = res.getColor(R.color.start_received);
        stopJobColor = res.getColor(R.color.stop_received);

        mDelayEditText = (EditText) findViewById(R.id.delay_time);
        mIntervalEditText = (EditText) findViewById(R.id.deadline_time);
        mServiceComponent = new ComponentName(this, TestJobService.class);

    }

    // UI fields.
    int defaultColor;
    int startJobColor;
    int stopJobColor;
    private EditText mDelayEditText;
    private EditText mIntervalEditText;
    ComponentName mServiceComponent;

    /** Service object to interact scheduled jobs. */

    TestJobService mTestService;
    private static int kJobId = 0;


    public void startAlarmManager(View v) {
        Context context = getBaseContext();
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        testJobReceiver = new Intent(context, TestJobReceiver.class);
        pendingJobIntent = PendingIntent.getBroadcast(this, 110, testJobReceiver, 0);

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                Integer.parseInt(mDelayEditText.getText().toString()) * 60000,
                Integer.parseInt(mIntervalEditText.getText().toString()) * 60000, // 60000 = 1 minutes
                pendingJobIntent);
    }

    public void cancelAlarmManager(View v) {
        if(alarmManager != null) {
            if (pendingJobIntent != null)
                alarmManager.cancel(pendingJobIntent);
        }
    }


}
