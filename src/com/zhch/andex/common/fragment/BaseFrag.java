package com.zhch.andex.common.fragment;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;

public class BaseFrag extends Fragment{

    private Toast toast;
    public Context getContext(){
        return getActivity();
    }

    /**
     * 显示 toast, Fragment范围内唯一
     * @param text
     */

    protected void showToast(CharSequence text){
        if(toast == null){
            toast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
        }else{
            toast.setText(text);
        }
        toast.show();
    }

    protected void showToast(int textRes){
        if(toast == null){
            toast = Toast.makeText(getActivity(), getString(textRes), Toast.LENGTH_LONG);
        }else{
            toast.setText(getString(textRes));
        }
        toast.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(toast != null){
            toast.cancel();
        }
    }
	
}
