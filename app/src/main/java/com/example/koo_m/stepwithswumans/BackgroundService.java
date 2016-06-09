package com.example.koo_m.stepwithswumans;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.ResultReceiver;

public class BackgroundService extends IntentService implements SensorEventListener {

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    ResultReceiver receiver = null;
    public static int backcount;
    boolean isRunning = true;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;


    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        backcount = intent.getExtras().getInt("count");
        receiver = intent.getParcelableExtra("receiver");

        String command = intent.getStringExtra("command");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (command.equals("increase count")) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            while (isRunning) {
                onStart();
            }

        }

    }

    public void onStart() {
        if (accelerormeterSensor != null)
            sensorManager.registerListener((SensorEventListener) this, accelerormeterSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    backcount++;
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }

        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onDestroy() {
        //카운트 값 증가시키는 루프를 중단시키고
        isRunning = false;

        //증가된 카운트 값을 리턴한다.
        Bundle b = new Bundle();
        try {
            b.putInt("back", backcount);
            receiver.send(STATUS_FINISHED, b);

        } catch (Exception e) {
            b.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, b);
        }
        Fragment2.textView.setText(backcount);
    }


}


