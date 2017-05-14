package com.sp.tojoin.biz;

import com.sp.tojoin.api.RegisterApi;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.presenter.EditPresenter;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/5/8.
 */

public class EditHelper {

    private EditPresenter presenter;

    private Retrofit retrofit;

    private RegisterApi registerApi;

    public EditHelper(EditPresenter presenter) {
        this.presenter=presenter;
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:4000/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        registerApi=retrofit.create(RegisterApi.class);
    }

    public void publishPassage(String uuid,String title,String content){
        String s="{ \"uuid\":" +"\""+uuid+"\",\"title\":\""+title+"\",\"content\":\""+content+"\"}";
        LogUtil.log("sendreq:",s);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),s);
        registerApi.publishPassage("insertpassage",requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try{
                            LogUtil.log("insertpassageRes:",responseBody.string());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.log("insertpassageRes","error");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.log("insertpassageRes","complete");
                    }
                });


    }
}
