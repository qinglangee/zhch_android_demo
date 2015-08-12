package com.zhch.andex.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-8-3.
 */
public class GravitySensorDemo extends BaseAct {
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Bind(R.id.btn_face)
    Button mFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_gravity_demo);
        ButterKnife.bind(this);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            List<Sensor> gravSensors = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            for (int i = 0; i < gravSensors.size(); i++) {
                if ((gravSensors.get(i).getVendor().contains("Google Inc.")) &&
                        (gravSensors.get(i).getVersion() == 3)) {
                    // Use the version 3 gravity sensor.
                    mSensor = gravSensors.get(i);
                }
            }

            if(mSensor == null){
                mSensor = gravSensors.get(0);
            }
        } else {
            // Use the accelerometer.
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            } else {
                // Sorry, there are no accelerometers on your device.
                // You can't play this game.
                showToast("Sorry, You can't play this game.");
            }
        }
    }
}
