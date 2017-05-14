package com.sp.tojoin.presenter;

import com.sp.tojoin.IListener.IEditActivity;
import com.sp.tojoin.biz.EditHelper;

/**
 * Created by Administrator on 2017/5/8.
 */

public class EditPresenter {

    private EditHelper editHelper;

    private IEditActivity iEditActivity;

    public EditPresenter(IEditActivity iEditActivity) {
        this.iEditActivity=iEditActivity;
        editHelper=new EditHelper(this);
    }
    public void publisPassage(String uuid,String title,String content){
        editHelper.publishPassage(uuid,title,content);
    }


}
