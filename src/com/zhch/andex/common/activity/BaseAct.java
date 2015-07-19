package com.zhch.andex.common.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class BaseAct extends Activity{


    private Toast toast;


    public Context getContext(){
        return this;
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
