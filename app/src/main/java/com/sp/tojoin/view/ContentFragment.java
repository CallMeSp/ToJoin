package com.sp.tojoin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.tojoin.IListener.IContentFragment;
import com.sp.tojoin.PassageDetailActivity;
import com.sp.tojoin.R;
import com.sp.tojoin.adapter.ContentAdapter;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.content;
import com.sp.tojoin.presenter.ContentPresent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ContentFragment extends Fragment implements IContentFragment{
    private static final String TAG = "ContentFragment";

    private RecyclerView recyclerView;

    private ContentPresent contentPresent=new ContentPresent(this);

    private ArrayList<content> contentlist=new ArrayList<>();

    private ContentAdapter adapter;

    public static ContentFragment getInstance(){
        ContentFragment contentFragment=new ContentFragment();
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentPresent.getContentList();
        adapter=new ContentAdapter(getContext(),contentlist);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_content,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_content);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setItemSelectedListener(new ContentAdapter.onItemSelectedListener() {
            @Override
            public void onChoose(String url) {
                String contenturl="http://192.168.137.1:4000/content/ufcab9ff029cd11e79762e72f53de85e3/"+url;
                LogUtil.log(TAG,"url:"+contenturl);
                Intent intent=new Intent(getActivity(), PassageDetailActivity.class);
                intent.putExtra("contenturl",contenturl);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void setContentArrayList(ArrayList<content> list) {
        this.contentlist.clear();
        this.contentlist.addAll(list);
        adapter.notifyDataSetChanged();
        for (int i=0;i<list.size();i++){
            LogUtil.log("setContentArrayList:",list.get(i).title+"  url:  http://localhost:4000/content/ufcab9ff029cd11e79762e72f53de85e3/"+list.get(i).contenturl);
        }

    }
}
