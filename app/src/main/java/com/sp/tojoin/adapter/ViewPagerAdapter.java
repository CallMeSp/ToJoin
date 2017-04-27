package com.sp.tojoin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sp.tojoin.view.ContentFragment;
import com.sp.tojoin.view.FavoutiteFragment;
import com.sp.tojoin.view.MessageFragment;
import com.sp.tojoin.view.MineFragment;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }
    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return MessageFragment.getInstance();
        }else if (position==1){
            return ContentFragment.getInstance();
        }else if (position==2){
            return FavoutiteFragment.getInstance();
        }else {
            return MineFragment.getInstance();
        }
    }
}
