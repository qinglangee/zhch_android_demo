package com.zhch.andex.activitydemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

/**
 * Created by zhch on 15-8-12.
 */
public class KilledActivityDemo extends BaseAct implements View.OnClickListener{

    RelativeLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new RelativeLayout(this);
        view.setId(R.id.linear);
        Button btn = new Button(this);
        btn.setText("Press");
        btn.setOnClickListener(this);
        view.addView(btn);
        setContentView(view);

    }

    @Override
    public void onClick(View v) {

        FragmentManager manager = getFragmentManager();

        Fragment frag = null;

//        frag = manager.findFragmentByTag("kill_frag");

        Log.d("@@@@@@@@@@@@@@@@@@@@", "Activity create  frag is null: " + (frag == null));

        if(frag == null){

            frag = new KilledActivityFrag();
            Bundle args = new Bundle();
            args.putString("arg", "from act");
            frag.setArguments(args);
        }


        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(view.getId(), frag, "kill_frag");

        transaction.commit();
    }
}
