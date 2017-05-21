package com.sp.tojoin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sp.tojoin.IListener.IReviewActivity;
import com.sp.tojoin.adapter.ReviewAdapter;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.Review;
import com.sp.tojoin.presenter.ReviewPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ReviewActivity extends Activity implements IReviewActivity{

    private static final String TAG = "ReviewActivity";

    private int id;

    private String writter;

    private String myuuid;

    private ReviewPresenter presenter=new ReviewPresenter(this);

    private ArrayList<Review> reviewArrayList=new ArrayList<>();

    private ReviewAdapter adapter;

    @BindView(R.id.edit_reviewcontent)EditText editText;

    @BindView(R.id.btn_sendreview)Button button;

    @BindView(R.id.recycler_reviewlist)RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        /**
         * 获取传入信息,根据writter，找到对应存储的文章
         * 1.获取评论列表。解析json.
         * 2.发表评论，即上传一个封装的json来表示
         */
        Intent intent=getIntent();
        id=Integer.valueOf(intent.getStringExtra("id"));
        writter=intent.getStringExtra("writter");
        myuuid=intent.getStringExtra("myuuid");
        LogUtil.log(TAG,"id:"+id+" writter:"+writter+"  myuuid:"+myuuid);

        presenter.getReviewList(writter,id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString();
                if (str!=null){
                    presenter.makeReview(str,myuuid,writter,id);
                }
            }
        });

        adapter=new ReviewAdapter(this,reviewArrayList);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList(ArrayList<Review> list) {
        reviewArrayList.clear();
        reviewArrayList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
