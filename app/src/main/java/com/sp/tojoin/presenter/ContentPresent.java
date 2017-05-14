package com.sp.tojoin.presenter;

import com.sp.tojoin.IListener.IContentFragment;
import com.sp.tojoin.biz.ContentHelper;
import com.sp.tojoin.model.content;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ContentPresent {
    private ContentHelper contentHelper;
    private IContentFragment iContentFragment;
    public ContentPresent(IContentFragment iContentFragment){
        this.iContentFragment=iContentFragment;
        contentHelper=new ContentHelper(this);
    }
    public void getContentList(String uuid){
        contentHelper.getContentList(uuid, new ContentHelper.ContentListListener() {
            @Override
            public void setContent(ArrayList<content> list) {
                iContentFragment.setContentArrayList(list);
            }
        });
    }

}
