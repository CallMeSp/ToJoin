package com.sp.tojoin.biz;

import com.sp.tojoin.api.RegisterApi;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.presenter.LoginPresenter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/4/16.
 */

public class LoginHelper {
    private Retrofit retrofit;
    private RegisterApi registerApi;
    private LoginPresenter loginPresenter;
    public LoginHelper(LoginPresenter presenter) {
        loginPresenter=presenter;
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:4000/")
                .client(new OkHttpClient())
                .build();
        registerApi=retrofit.create(RegisterApi.class);
    }
    public void sendReqToGetEmail(String address){
        String s="{ \"address\":" +"\""+address+"\"}";
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),s);
        Call<ResponseBody> call=registerApi.getEmail("getEmail",requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    if (res.equals("fail")){
                        loginPresenter.makeToast("该邮箱已被注册请直接登陆");
                    }else {
                        loginPresenter.makeToast("邮件已发送注意查收");
                    }
                    LogUtil.log("response",": "+res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
    public void sendReqToRegister(String address, String pwd, int num){
        loginPresenter.showBar();
        String s="{ \"address\":" +"\""+address+"\","+
                "\"pwd\":"+"\""+pwd+"\","+
                "\"num\":"+num+
                "}";
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),s);
        Call<ResponseBody> call=registerApi.toRegister("register",requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    if (res.equals("registersuccess")){
                        loginPresenter.makeToast("注册成功 ");
                    }
                    loginPresenter.hideBar();
                    LogUtil.log("response",": "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
    public void sendReqToLogin(String address,String pwd){
        loginPresenter.showBar();
        String s="{ \"address\":" +"\""+address+"\","+
                "\"pwd\":"+"\""+pwd+"\""+
                "}";
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),s);
        Call<ResponseBody> call=registerApi.toLogin("login",requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    LogUtil.log("login",""+res);
                    if (res.equals("loginFail")){
                        loginPresenter.loginFail();
                    }else if (res.equals("loginSuc")){
                        loginPresenter.loginSuc();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
