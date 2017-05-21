package com.sp.tojoin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.tojoin.R;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.content;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/3.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

    private onItemSelectedListener listener;
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.txt_item);
        }
    }

    private ArrayList<content> titleslist=new ArrayList<>();

    private LayoutInflater inflater;

    private Context context;

    public ContentAdapter(Context context,ArrayList<content> arrayList) {
        LogUtil.log("adaptercreate","..");
        this.titleslist=arrayList;
        this.inflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return titleslist.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.frag_content_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        LogUtil.log("bindview:",titleslist.get(position).title);
        holder.title.setText(titleslist.get(position).title);
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url=titleslist.get(position).contenturl;
                    int id=titleslist.get(position).id+1;
                    String writter=titleslist.get(position).writter;
                    listener.onChoose(url,writter,id);
                }
            });
        }
    }

    public interface onItemSelectedListener{
        void onChoose(String url,String writter,int id);
    }

    public void setItemSelectedListener(onItemSelectedListener listener){
        this.listener=listener;
    }


}
