package com.zhch.andex.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-8-3.
 */
public class AccelerateSensorDemo extends BaseAct implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mAccSensor;


    @Bind(R.id.info)
    TextView mInfo;

    int dataCount = 30;
    Queue<String> datas;

    float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_accelerate_demo);
        ButterKnife.bind(this);

        mSensorManager = (SensorManager) (getContext().getSystemService(Context.SENSOR_SERVICE));
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        datas = new ArrayBlockingQueue<String>(dataCount);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final float alpha = 0.8F;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];



        if (datas.size() == dataCount) {
            datas.remove();
        }
        datas.add("data  x: " + linear_acceleration[0] + " y: " + linear_acceleration[1] + " z: " + linear_acceleration[2]);

        StringBuilder sb = new StringBuilder();
        for (String s : datas) {
            sb.append(s).append("\n");
        }
        mInfo.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccSensor, 4000000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
