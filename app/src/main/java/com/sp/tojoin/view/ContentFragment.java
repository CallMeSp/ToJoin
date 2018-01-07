package com.sp.tojoin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ContentFragment extends Fragment implements IContentFragment{
    private static final String TAG = "ContentFragment";

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ContentPresent contentPresent=new ContentPresent(this);

    private ArrayList<content> contentlist=new ArrayList<>();

    private ContentAdapter adapter;

    private String myuuid;


    public static ContentFragment getInstance(){
        ContentFragment contentFragment=new ContentFragment();
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter=new ContentAdapter(getContext(),contentlist);
        myuuid=getActivity().getIntent().getStringExtra("uuid");
        LogUtil.log("myuuid",myuuid);

        //myuuid="common";
        contentPresent.getContentList("common");
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
            public void onChoose(String url,String writter,int id) {
                String contenturl="http://192.168.137.1:4000/content/"+url;
                LogUtil.log(TAG,"url:"+contenturl);
                Intent intent=new Intent(getActivity(), PassageDetailActivity.class);
                intent.putExtra("contenturl",contenturl);
                intent.putExtra("id",id+"");
                intent.putExtra("writter",writter);
                intent.putExtra("myuuid",myuuid);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefrsh);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue,R.color.lightblue,R.color.darkblue,R.color.cadetblue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //contentPresent.getContentList(myuuid);
                contentPresent.getContentList("common");
            }
        });


        return view;
    }

    @Override
    public void setContentArrayList(ArrayList<content> list) {
        this.contentlist.clear();
        this.contentlist.addAll(list);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        for (int i=0;i<list.size();i++){
            LogUtil.log("setContentArrayList:","id:"+list.get(i).id+" writter:"+list.get(i).writter+"  title"+list.get(i).title+"   http://192.168.137.1:4000/content/"+list.get(i).contenturl);
        }

    }
}
