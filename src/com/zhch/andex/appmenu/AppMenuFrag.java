package com.zhch.andex.appmenu;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhch.andex.R;
import com.zhch.andex.common.fragment.BaseFrag;
import com.zhch.andex.util.LL;

/**
 * Created by zhch on 15-7-28.
 */
public class AppMenuFrag extends TypeMenuFrag {

    private static String TAG = "AppMenuFrag";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_menu_page, container, false);
        mButtonBox = (LinearLayout) view.findViewById(R.id.button_box);

        initView(view);
        return view;
    }

    private void initView(View view) {
        items = new ButtonItem[]{
                new ButtonItem(CustomViewMenuFrag.class, "自定义View")
                ,new ButtonItem(WidgetMenuFrag.class, "系统控件")
                ,new ButtonItem(SensorMenuFrag.class, "传感器")
        };
        LL.d("1 items is null:" + (items == null));
        addFragButtons();
    }

    private void addFragButtons() {
        LL.d("2 items is null:" + (items == null));
        for(final ButtonItem item : items){
            Button btn = new Button(getContext());
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setText(item.name);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    try {
                        BaseFrag frag = (BaseFrag)(item.cla.newInstance());
                        fragmentTransaction.replace(R.id.fragment_container, frag);
                        fragmentTransaction.addToBackStack(null);
                    } catch (java.lang.InstantiationException e) {
                        Log.e(TAG, "create frag error.", e);
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "create frag error.", e);
                    }
                    fragmentTransaction.commit();
                }
            });
            mButtonBox.addView(btn);
        }
    }

}
