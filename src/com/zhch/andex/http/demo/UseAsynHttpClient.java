package com.zhch.andex.http.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhch on 15-7-1.
 */
public class UseAsynHttpClient extends BaseAct {

    @Bind(R.id.output)
    EditText mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_demo_basic_asyn);
        ButterKnife.bind(this);
    }


    /**
     * 最基础用法，新建然后请求一个　url
     *
     * @param v
     */
    public void getBaidu(View v) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String responseText = new String(response);
                mOutput.setText(responseText);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }
}
