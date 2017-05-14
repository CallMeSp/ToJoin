package com.sp.tojoin.presenter;

import com.sp.tojoin.IListener.ILoginActivity;
import com.sp.tojoin.biz.LoginHelper;

/**
 * Created by Administrator on 2017/4/18.
 */

public class LoginPresenter {

    private ILoginActivity iLoginActivity;

    private LoginHelper loginHelper;


    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity=iLoginActivity;
        this.loginHelper=new LoginHelper(this);
    }
    public void getEmail(String address){
        loginHelper.sendReqToGetEmail(address);
    }
    public void register(String address,String username,String pwd,int check){
        loginHelper.sendReqToRegister(address,username,pwd,check);
    }
    public void makeToast(String str){
        iLoginActivity.makeToast(str);
    }
    public void showBar(){
        iLoginActivity.showProgressBar();
    }
    public void hideBar(){
        iLoginActivity.hideProgressBar();
    }
    public void login(String address,String pwd){
        //loginHelper.sendReqToLogin(address,pwd);
        loginHelper.sendReqToLogin(address,pwd);
    }
    public void loginSuc(String uuid){
        iLoginActivity.loginSuccess(uuid);
    }
    public void loginFail(){
        iLoginActivity.loginFail();
    }

}
