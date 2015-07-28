package com.zhch.andex.image;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhch.andex.R;
import com.zhch.andex.adapter.CustomAdapter;
import com.zhch.andex.image.vo.BaiduImage;

/**
 * Created by zhch on 15-7-27.
 */
public class BaiduImageAdapter extends CustomAdapter<BaiduImage> {
    public BaiduImageAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    public void bindView(View view, int position, BaiduImage data) {
        SimpleDraweeView image = (SimpleDraweeView)view.findViewById(R.id.image);
        if(data.thumbURL != null){
            image.setImageURI(Uri.parse(data.thumbURL));
        }
    }
}
