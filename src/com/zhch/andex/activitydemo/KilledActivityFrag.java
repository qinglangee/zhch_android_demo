package com.zhch.andex.activitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhch.andex.R;
import com.zhch.andex.common.fragment.BaseFrag;

/**
 * Created by zhch on 15-8-12.
 */
public class KilledActivityFrag extends BaseFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text = new TextView(getContext());
        String str = getResources().getString(R.string.app_name);

        text.setText("ha ha ha, I'm fragment." + str);

        Bundle arguments = getArguments();
        final String pre;
        if(arguments == null){
            pre = "HAVE NOT ARGS";
        }else {
            String arg = arguments.getString("arg");
            pre = "ARGS IS :" + arg;
        }

        Activity act = getActivity();
        Log.d("@@@@@@@@@@@@@@@@@@@@", pre + " on create act is null : " + (act == null));

        StringRequest request = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Activity act = getActivity();
                Log.d("@@@@@@@@@@@@@@@@@@@@", pre + " listener act is null : " + (act == null));
                getActivity().getResources();
            }
        }, null);

        Volley.newRequestQueue(getActivity().getApplicationContext()).add(request);

        return text;
    }
}
