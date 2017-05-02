package com.sp.tojoin.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.tojoin.IListener.IContentFragment;
import com.sp.tojoin.R;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.content;
import com.sp.tojoin.presenter.ContentPresent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ContentFragment extends Fragment implements IContentFragment{
    private ContentPresent contentPresent=new ContentPresent(this);

    public static ContentFragment getInstance(){
        ContentFragment contentFragment=new ContentFragment();
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentPresent.getContentList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_content,container,false);
        return view;
    }

    @Override
    public void setContentArrayList(ArrayList<content> list) {
        for (int i=0;i<list.size();i++){
            LogUtil.log("setContentArrayList:",list.get(i).title);
        }
    }
}
