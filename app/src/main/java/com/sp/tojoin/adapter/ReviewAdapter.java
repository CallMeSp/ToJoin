package com.sp.tojoin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.tojoin.R;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.Review;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_review;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_review=(TextView)itemView.findViewById(R.id.text_review_content);
        }
    }
    private LayoutInflater inflater;

    private Context context;

    private ArrayList<Review> reviewArrayList=new ArrayList<>();

    public ReviewAdapter(Context context,ArrayList<Review> arrayList) {
        LogUtil.log("adaptercreate","..");
        this.reviewArrayList=arrayList;
        this.inflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_review_item,parent,false);
        ReviewAdapter.MyViewHolder viewHolder=new ReviewAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, final int position) {
        LogUtil.log("bindview:",reviewArrayList.get(position).content);
        holder.tv_review.setText(reviewArrayList.get(position).content);
    }

}
