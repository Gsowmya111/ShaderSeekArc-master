package me.rorschach.shaderseekarc;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ShaderSeekArc";
    private TextView timerValue;

    private long startTime = 0L;
    private long stopTime = 0L;
    Button b;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerValue = (TextView) findViewById(R.id.timerValue);

        b = (Button) findViewById(R.id.startButton);

//
//        ShaderSeekArc seekArc = (ShaderSeekArc) findViewById(R.id.seek_arc);
//
//        int[] colors = new int[]{0xFF2C3EFF, 0xFF53FF65, 0xFF000000};
//        seekArc.setColors(colors);
//
//        float[] positions = new float[]{0, 1/2f, 1};
//        seekArc.setPositions(positions);
//
//        seekArc.setStartColor(0xFFFF2D0C);
//        seekArc.setEndColor(0xFF1636FF);
//
//        seekArc.setStartValue(20);
//        seekArc.setEndValue(30);
//        seekArc.setProgress(25);
//
//        seekArc.setStartAngle(-180);
//        seekArc.setEndAngle(180);
//
//        seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
//            @Override
//            public void onProgressChanged(ShaderSeekArc seekArc, float progress) {
//                Log.d(TAG, "progress " + progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(ShaderSeekArc seekArc) {
//                Log.d(TAG, "onStartTrackingTouch");
//            }
//
//            @Override
//            public void onStopTrackingTouch(ShaderSeekArc seekArc) {
//                Log.d(TAG, "onStopTrackingTouch");
//            }
//        });




        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });



    }



    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);

           timerValue.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));


            String data = ("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            String[] items = data.split(":");



            String s = ("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));

            String[] arrayString = s.split(":");

            String email = arrayString[0];
            String title = arrayString[1];
            String body = arrayString[2];
            if(title.equals("1")){
                Toast.makeText(MainActivity.this,"Hiiiiiiii",Toast.LENGTH_LONG).show();
            }



            /*if(timerValue.getText().toString().equals("" + mins + ":"
                    + String.format("%02d", 5) + ":"
                    + String.format("%03d", 800))){
                Toast.makeText(MainActivity.this,"Hiiiiiiii",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(MainActivity.this,"Heloo",Toast.LENGTH_LONG).show();
            }*/



            customHandler.postDelayed(this, 0);


        }

    };










}
