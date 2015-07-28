package com.zhch.andex.system;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-7-27.
 */
public class SystemDataDemo extends BaseAct {

    @Bind(R.id.memory)
    TextView mMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_data_demo);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int m = 0;
        if (maxMemory > 1024) {
            m = maxMemory / 1024;
            maxMemory = maxMemory % 1024;
        }

        mMemory.setText("可用内存: " + m + "M " + maxMemory + "K");
    }


}
