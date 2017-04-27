package com.sp.tojoin.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.tojoin.R;

/**
 * Created by Administrator on 2017/4/20.
 */

public class FavoutiteFragment extends Fragment {
    public static FavoutiteFragment getInstance(){
        FavoutiteFragment contentFragment=new FavoutiteFragment();
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_favourite,container,false);
        return view;
    }
}
