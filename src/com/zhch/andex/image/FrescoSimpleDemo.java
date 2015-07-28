package com.zhch.andex.image;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.constant.Api;
import com.zhch.andex.constant.BaiduImageApi;
import com.zhch.andex.image.vo.BaiduImage;
import com.zhch.andex.image.vo.BaiduImageResp;
import com.zhch.andex.util.net.GsonRequest;
import com.zhch.andex.util.net.NetUtil;
import com.zhch.andex.util.net.VolleyWrapper;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-7-27.
 */
public class FrescoSimpleDemo extends BaseAct {

    @Bind(R.id.images)
    ListView mImages;
    BaiduImageAdapter imageAdapter;

    @Bind(R.id.types)
    Spinner mTypes;
    ArrayAdapter<CharSequence> typeAdapter;

    String searchWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_fresco_simple_demo);
        ButterKnife.bind(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        typeAdapter = ArrayAdapter.createFromResource(this, R.array.baidu_img_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mTypes.setAdapter(typeAdapter);

        imageAdapter = new BaiduImageAdapter(this, R.layout.image_baidu_image_item);
        mImages.setAdapter(imageAdapter);

        addListeners();


        loadData();
    }

    private void addListeners() {
        // 类型选择时的 listener
        mTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence item = typeAdapter.getItem(position);
                searchWord = item.toString();
                loadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadData() {
        Api api = BaiduImageApi.IMAGE_SEARCH;
        // 主要参数： word，查询关键词 rn，每页显示图片数量 pn，图片显示的页码
        Map<String, String> params = NetUtil.createParams("tn", "baiduimagejson");
        params.put("rn", "20");
        params.put("pn", "1");
        params.put("word", searchWord == null ? "美女" : searchWord);

        GsonRequest<BaiduImageResp> request = new GsonRequest<BaiduImageResp>(api, BaiduImageResp.class, params, getImageListener(), NetUtil.getErrorListener(this));

        VolleyWrapper.getInstance(this).addToRequestQueue(request);
    }

    private Response.Listener<BaiduImageResp> getImageListener() {
        return new Response.Listener<BaiduImageResp>() {
            @Override
            public void onResponse(BaiduImageResp response) {
                List<BaiduImage> data = response.data;
                imageAdapter.updateData(data);
            }
        };
    }

}
