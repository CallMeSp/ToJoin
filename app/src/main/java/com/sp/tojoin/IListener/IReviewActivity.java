package com.sp.tojoin.IListener;

import com.sp.tojoin.model.Review;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public interface IReviewActivity {
    void updateList(ArrayList<Review> list);
    void initialize();
}
