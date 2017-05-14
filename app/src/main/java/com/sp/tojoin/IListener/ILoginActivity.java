package com.sp.tojoin.IListener;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface ILoginActivity {
    void showProgressBar();
    void hideProgressBar();
    void makeToast(String str);
    void loginSuccess(String uuid);
    void loginFail();
}
