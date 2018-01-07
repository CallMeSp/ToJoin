package com.sp.tojoin.biz;

import com.sp.tojoin.api.RegisterApi;
import com.sp.tojoin.base.JsonUtils;
import com.sp.tojoin.base.LogUtil;
import com.sp.tojoin.model.ReviewModel;
import com.sp.tojoin.presenter.ReviewPresenter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Created by Administrator on 2017/5/22.
 */

public class ReviewHelper {

    private static final String TAG = "ReviewHelper";

    private ReviewPresenter reviewPresenter;

    private Retrofit retrofit;

    private RegisterApi registerApi;

    public ReviewHelper(ReviewPresenter presenter){
        reviewPresenter=presenter;
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:4000/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        registerApi=retrofit.create(RegisterApi.class);
    }

    public void getReviewList(String writter,int id){
        String str=JsonUtils.Builder()
                .addItem("writter",writter)
                .addItem("id",id)
                .build();
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),str);
        registerApi.getReviewList("getReviewList",requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReviewModel>() {
                    @Override
                    public void onNext(ReviewModel reviewModel) {
                        reviewPresenter.updateList(reviewModel.reviewlist);
                        for (int i=0;i<reviewModel.reviewlist.size();i++){
                            LogUtil.log(TAG,"review: from who:"+reviewModel.reviewlist.get(i).fromwho
                                    +"  content:" +reviewModel.reviewlist.get(i).content
                                    +"  time:"+reviewModel.reviewlist.get(i).time);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.log(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.log(TAG,"onComplete");
                    }
                });
    }

    public void makeReview(String content, String myuuid, String writter, int id, final iReviewResultListener listener){
        //获取当前时间
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd日 HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());
        String time=formatter.format(curDate);

        //合成json信息
        String str=JsonUtils.Builder()
                .addItem("fromwho",myuuid)
                .addItem("content",content)
                .addItem("time",time)
                .addItem("id",id)
                .addItem("writter",writter)
                .build();
        LogUtil.log(TAG,"req:"+str);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),str);
        registerApi.makeReview("makeReview",requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String res=responseBody.string();
                            LogUtil.log(TAG,"onNext:"+res);
                            if (res.equals("ReviewSuc")){
                                LogUtil.log(TAG,"listenerSuc");
                                listener.getResult(true);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.log(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.log(TAG,"onComplete");
                    }
                });

    }
    public interface iReviewResultListener{
        void getResult(Boolean result);
    }
}
