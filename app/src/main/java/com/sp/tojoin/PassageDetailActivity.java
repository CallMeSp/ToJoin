package com.sp.tojoin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sp.tojoin.base.JsonUtils;
import com.sp.tojoin.base.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/23.
 */

public class PassageDetailActivity extends Activity {

    private static final String TAG = "PassageDetailActivity";


    @BindView(R.id.webView)WebView webView;

    @BindView(R.id.rootlaytout)LinearLayout linearLayout;

    @BindView(R.id.btn_review)Button btn_review;


    //文章的序号和作者，从intent中获取，用于访问文章。
    int id;
    String writter;
    String myuuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passagedetail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("contenturl");
        id=Integer.valueOf(intent.getStringExtra("id"));
        writter=intent.getStringExtra("writter");
        myuuid=intent.getStringExtra("myuuid");
        LogUtil.log(TAG,"url:"+url);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PassageDetailActivity.this,ReviewActivity.class);
                intent1.putExtra("writter",writter);
                intent1.putExtra("id",id+"");
                intent1.putExtra("myuuid",myuuid);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        linearLayout.removeView(webView);
        webView.destroy();
    }
}
