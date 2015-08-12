package com.zhch.andex.common.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.zhch.andex.util.PreferencesUtils;

public class BaseAct extends Activity{

    public static final String CURRENT_ACTIVITY = "CURRENT_ACTIVITY";


    private Toast toast;


    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(this.getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferencesUtils.putString(this, CURRENT_ACTIVITY, this.getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(toast != null){
            toast.cancel();
        }
    }

    protected void showToast(CharSequence text){
        if(toast == null){
            toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        }else{
            toast.setText(text);
        }
        toast.show();
    }

    protected void showToast(int textRes){
        if(toast == null){
            toast = Toast.makeText(this, getString(textRes), Toast.LENGTH_LONG);
        }else{
            toast.setText(getString(textRes));
        }
        toast.show();
    }



}
