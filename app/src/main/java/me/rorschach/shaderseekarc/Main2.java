package me.rorschach.shaderseekarc;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

public class Main2 extends Activity {

    TextView tvData;
    Chronometer chronometer;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private long startTime = 0L;
    private long stopTime = 0L;
    private Handler customHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv2);

        tvData = (TextView) findViewById(R.id.tv_data);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        startChronometer();

        chronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
            public void onChronometerTick(Chronometer cArg) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, getCurrentMiliSecondsOfChronometer());
                tvData.setText(DateFormat.format("HH:mm:ss:mm", calendar.getTime()));
            }
        });
    }

    private int getCurrentMiliSecondsOfChronometer() {
        int stoppedMilliseconds = 0;
        String chronoText = chronometer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds =
                    Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000
                            + Integer.parseInt(array[2]) * 1000;
        }
        return stoppedMilliseconds;
    }

    private void startChronometer() {
       /* int stoppedMilliseconds = getCurrentMiliSecondsOfChronometer();
        chronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
        chronometer.start();*/



        timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

        updatedTime = timeSwapBuff + timeInMilliseconds;

        int secs = (int) (updatedTime / 1000);
        int mins = secs / 60;
        secs = secs % 60;
        int milliseconds = (int) (updatedTime % 1000);

        tvData.setText("" + mins + ":"
                + String.format("%02d", secs) + ":"
                + String.format("%03d", milliseconds));

     //   customHandler.postDelayed(this, 0);


    }




}