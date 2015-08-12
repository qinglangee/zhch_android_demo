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
    protected void initViewField(View view) {
        ViewHolder holder = new ViewHolder();
        holder.image = (SimpleDraweeView)view.findViewById(R.id.image);
        view.setTag(holder);
    }

    @Override
    public void bindView(View view, int position, BaiduImage data) {
        ViewHolder holder = (ViewHolder)view.getTag();
        if(data.thumbURL != null){
            holder.image.setImageURI(Uri.parse(data.thumbURL));
        }
    }

    static class ViewHolder {
        SimpleDraweeView image;
    }
}
