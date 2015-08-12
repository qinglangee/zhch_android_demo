package com.zhch.andex.syswidget;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-7-30.
 */
public class SmallButtonsDemo extends BaseAct {

    @Bind(R.id.toggle_btn_01)
    ToggleButton mToggle01;

    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.date_picker)
    DatePicker mDatePicker;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.time_picker)
    TimePicker mTimePicker;

    @Bind(R.id.radio_sun)
    ImageView mRadioSun;
    @Bind(R.id.radio_sun_on)
    RadioButton mRadioSunOn;
    @Bind(R.id.radio_sun_off)
    RadioButton mRadioSunOff;

    @Bind(R.id.img_check_sun)
    ImageView mImgCheckSun;
    @Bind(R.id.check_sun)
    CheckBox mCheckSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syswidget_small_buttons_demo);

        ButterKnife.bind(this);


        bindListener();
    }

    private void bindListener() {

        mToggle01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showToast("Toggle 01 checked");
                } else {
                    showToast("Toggle 01 is not checked");
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate.setText("您选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日。");
            }
        });

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTime.setText("您选择的时间是：" + hourOfDay + "时" + minute + "分。");
            }

        });

        mRadioSunOn.setOnCheckedChangeListener(changedListener);
        mRadioSunOff.setOnCheckedChangeListener(changedListener);
        mCheckSun.setOnCheckedChangeListener(changedListener);

    }

    private CompoundButton.OnCheckedChangeListener changedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.radio_sun_on:
                    if (isChecked)
                        mRadioSun.setImageResource(R.drawable.sun_on);
                    break;
                case R.id.radio_sun_off:
                    if (isChecked)
                        mRadioSun.setImageResource(R.drawable.sun_off);
                    break;
                case R.id.check_sun:
                    mImgCheckSun.setImageResource(isChecked ? R.drawable.sun_on : R.drawable.sun_off);
            }
        }
    };
}
