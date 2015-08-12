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
public class LightSensorDemo extends BaseAct implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccSensor;

    @Bind(R.id.info)
    TextView mInfo;

    int dataCount = 15;
    Queue<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_light_demo);
        ButterKnife.bind(this);

        mSensorManager = (SensorManager) (getContext().getSystemService(Context.SENSOR_SERVICE));
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        datas = new ArrayBlockingQueue<String>(dataCount);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        float lux = event.values[0];
        // Do something with this sensor value.

        if (datas.size() == dataCount) {
            datas.remove();
        }
        datas.add("light: " + lux);

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
        mSensorManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
