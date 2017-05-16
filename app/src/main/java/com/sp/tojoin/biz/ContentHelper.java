package com.sp.tojoin.biz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.sp.tojoin.R;
import com.sp.tojoin.api.RegisterApi;
import com.sp.tojoin.base.JsonUtils;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.ContentModel;
import com.sp.tojoin.model.content;
import com.sp.tojoin.presenter.ContentPresent;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ContentHelper {
    private Retrofit retrofit;
    private RegisterApi registerApi;
    private ContentPresent contentPresent;
    public ContentHelper(ContentPresent present){
        contentPresent=present;
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:4000/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        registerApi=retrofit.create(RegisterApi.class);
    }
    public void getContentList(String uuid, final ContentListListener contentListListener){
        //String s="{ \"uuid\":" +"\""+uuid+"\"}";
        String s= JsonUtils.Builder()
                .addItem("uuid",uuid)
                .build();
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),s);
        registerApi.getContentList("getMyArticleList",requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ContentModel>() {
                    @Override
                    public void onNext(ContentModel contentModel) {
                        contentListListener.setContent(contentModel.list);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void makeComments(String uuid,String id,String content){

    }
    /*public void uploadImg(){
        Resources resources=Resources.getSystem();
        Bitmap bitmap=BitmapFactory.decodeResource(resources,R.drawable.splash);

        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

// 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

// 执行请求
        Call<ResponseBody> call = registerApi.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }*/
    public interface ContentListListener{
        void setContent(ArrayList<content> list);
    }
}

