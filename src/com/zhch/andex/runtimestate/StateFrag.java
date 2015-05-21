package com.zhch.andex.runtimestate;

import android.os.Bundle;

import com.zhch.andex.common.fragment.BaseFrag;

public class StateFrag extends BaseFrag{

    // data object we want to retain
    private MyDataObject data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(MyDataObject data) {
        this.data = data;
    }

    public MyDataObject getData() {
        return data;
    }
    
    public class MyDataObject{
    	
    }
}

