package com.zhch.andex.syswidget;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.adapter.CustomAdapter;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.image.vo.BaiduImage;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-7-28.
 */
public class SpinnerDemo extends BaseAct {
    @Bind(R.id.simple)
    Spinner mSimple;
    private CustomAdapter simpleAdapter;

    @Bind(R.id.editable)
    Spinner mEditable;

    @Bind(R.id.autocomplete)
    AutoCompleteTextView mAutocomplete;
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syswidget_spinner_demo);
        ButterKnife.bind(this);

        createAdapter();
        mSimple.setAdapter(simpleAdapter);

        List<String> list = Arrays.asList(new String[]{"Demo01", "Demo02", "Demo03"});
        simpleAdapter.updateData(list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        mAutocomplete.setThreshold(0); // 这里最少是1, 0不管用
        mAutocomplete.setAdapter(adapter);
    }

    private void createAdapter() {
//        simpleAdapter = new
        simpleAdapter = new CustomAdapter<String>(this, null) {


            @Override
            protected View createNewView() {
                TextView t = new TextView(mContext);
                return t;
            }

            @Override
            protected void initViewField(View view) {
                ViewHolder holder = new ViewHolder();
                holder.text = (TextView) view;
                view.setTag(holder);
            }

            @Override
            public void bindView(View view, int position, String data) {
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.text.setText(data);
            }

            class ViewHolder {
                TextView text;
            }
        };
    }
}
