package com.zhch.andex.appmenu;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhch.andex.common.fragment.BaseFrag;
import com.zhch.andex.util.LL;

/**
 * Created by zhch on 15-7-28.
 */
public class TypeMenuFrag extends BaseFrag {

    protected LinearLayout mButtonBox;
    protected ButtonItem[] items;

    public class ButtonItem{
        public Class cla;
        public String name;
        public ButtonItem(Class cla){
            this(cla, cla.getSimpleName());
        }
        public ButtonItem(Class cla, String name){
            this.cla = cla;
            this.name = name;
        }
    }



    protected void addButtons() {
        LL.d("2 items is null:" + (items == null));
        for(final ButtonItem item : items){
            Button btn = new Button(getContext());
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setText(item.name);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), item.cla);
                    startActivity(intent);
                }
            });
            mButtonBox.addView(btn);
        }
    }
}
