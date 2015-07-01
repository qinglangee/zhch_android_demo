package com.zhch.andex.customview.demo;

import android.app.Activity;
import android.os.Bundle;

import com.zhch.andex.R;
import com.zhch.andex.customview.CircleProgressBar;
import com.zhch.andex.customview.CustomDrawableView;

public class UseCircleProgressBar extends Activity {

    CircleProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview_circle_progress_bar);
        progressBar = (CircleProgressBar) findViewById(R.id.circleProgressbar);

//        progressBar = new CircleProgressBar(this);
//        setContentView(progressBar);


        new Thread() {
            public void run() {
                int i = 0;
                while (i <= 100) {
                    progressBar.setProgressNotInUiThread(i);
                    i++;
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
