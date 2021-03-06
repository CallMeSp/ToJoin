package com.sp.tojoin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sp.tojoin.adapter.ViewPagerAdapter;
import com.sp.tojoin.base.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MainActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener{
    @BindView(R.id.main_bottomBar)BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.viewpager)ViewPager pager;
    @BindView(R.id.floatbutton)FloatingActionButton floatingActionButton;
    private String myuuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.comments,"消息"))
                .addItem(new BottomNavigationItem(R.drawable.viewlist,"内容"))
                .addItem(new BottomNavigationItem(R.drawable.pin,"收藏"))
                .addItem(new BottomNavigationItem(R.drawable.account,"个人"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(3);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                intent.putExtra("uuid",myuuid);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        myuuid=intent.getStringExtra("uuid");
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onTabSelected(int position) {
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }
}
