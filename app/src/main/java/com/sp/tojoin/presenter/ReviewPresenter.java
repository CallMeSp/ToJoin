package com.sp.tojoin.presenter;

import com.sp.tojoin.IListener.IReviewActivity;
import com.sp.tojoin.biz.ReviewHelper;
import com.sp.tojoin.model.Review;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public class ReviewPresenter {

    private static final String TAG = "ReviewPresenter";

    private IReviewActivity iReviewActivity;

    private ReviewHelper reviewHelper;

    public ReviewPresenter(IReviewActivity iReviewActivity){
        this.iReviewActivity=iReviewActivity;
        reviewHelper=new ReviewHelper(this);
    }

    public void getReviewList(String writter,int id){
        reviewHelper.getReviewList(writter,id);
    }

    public void makeReview(String content,String myuuid,String writter,int id){
        reviewHelper.makeReview(content,myuuid,writter,id);
    }

    public void updateList(ArrayList<Review> list){
        iReviewActivity.updateList(list);
    }


}
