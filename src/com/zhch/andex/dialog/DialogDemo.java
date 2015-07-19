package com.zhch.andex.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import java.util.Date;

/**
 * Created by zhch on 15-7-6.
 */
public class DialogDemo extends BaseAct{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_demo_page);
    }

    public void alertDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("软件版本更新");
        builder.setMessage("要更新了哦");
        builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DialogDemo.this.showToast("点了下载哦");
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);  // 可以设置
        AlertDialog dia = builder.create();
        dia.show();
    }
}
