package com.zhch.andex.appmenu;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.sensor.AccelerateSensorDemo;
import com.zhch.andex.sensor.GravitySensorDemo;
import com.zhch.andex.sensor.LightSensorDemo;
import com.zhch.andex.sensor.MagneticSensorDemo;
import com.zhch.andex.syswidget.TabHostDemo;

import java.util.List;

/**
 * 传感器
 * Created by zhch on 15-7-28.
 */
public class SensorMenuFrag extends TypeMenuFrag {
    private SensorManager mSensorManager;

    // 磁力传感器测试
    TextView mSingelInfo;

    // show Sensor list
    TextView mInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_menu_page, container, false);
        mButtonBox = (LinearLayout) view.findViewById(R.id.button_box);

        items = new ButtonItem[]{
                new ButtonItem(AccelerateSensorDemo.class, "加速度传感器")
                , new ButtonItem(GravitySensorDemo.class, "重力传感器")
                , new ButtonItem(MagneticSensorDemo.class, "磁力传感器")
                , new ButtonItem(LightSensorDemo.class, "光线传感器")
        };
        addButtons();

        addTextViews(mButtonBox);
        return view;
    }

    private void addTextViews(LinearLayout layout) {
        mSingelInfo = new TextView(getContext());
        layout.addView(mSingelInfo);


        mInfo = new TextView(getContext());
        layout.addView(mInfo);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSensorManager = (SensorManager) (getContext().getSystemService(Context.SENSOR_SERVICE));
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sb = new StringBuilder("Sensor list:");
        for (Sensor sensor : deviceSensors) {
            sb.append("\n[ ").append(sensor.getType()).append(" - ").append(sensor.getName()).append("]  :  ").append(sensor.getVendor());
        }
        mInfo.setText(sb.toString());


        mSingelInfo.setText("");
        setSingleSensorInfo(Sensor.TYPE_ACCELEROMETER, "加速度传感器");
        setSingleSensorInfo(Sensor.TYPE_LINEAR_ACCELERATION, "线性加速传感器");
        setSingleSensorInfo(Sensor.TYPE_MAGNETIC_FIELD, "磁力传感器");
        setSingleSensorInfo(Sensor.TYPE_GRAVITY, "重力传感器");
        setSingleSensorInfo(Sensor.TYPE_GYROSCOPE, "陀螺仪传感器");
        setSingleSensorInfo(Sensor.TYPE_LIGHT, "光线传感器");
        setSingleSensorInfo(Sensor.TYPE_TEMPERATURE, "温度传感器");
        setSingleSensorInfo(Sensor.TYPE_AMBIENT_TEMPERATURE, "新的温度传感器");
        setSingleSensorInfo(Sensor.TYPE_PRESSURE, "气压传感器");
    }

    private void setSingleSensorInfo(int sensorType, String name){
        Sensor sensor = mSensorManager.getDefaultSensor(sensorType);
        String content = mSingelInfo.getText().toString();
        if(sensor != null){
            content += name + "  Get" + "\n";
        }else{
            content += name + "  没有!!!" + "\n";
        }
        mSingelInfo.setText(content);
    }
}
