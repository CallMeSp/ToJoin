package com.sp.tojoin.IActivity;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface ILoginActivity {
    void showProgressBar();
    void hideProgressBar();
    void makeToast(String str);
    void loginSuccess();
    void loginFail();
}
