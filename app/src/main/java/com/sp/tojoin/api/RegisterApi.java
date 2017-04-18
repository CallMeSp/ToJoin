package com.sp.tojoin.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/16.
 */

public interface RegisterApi {

    @POST("http://192.168.137.1:4000/register")
    Call<ResponseBody> getEmail(@Header("method") String head,@Body RequestBody requestBody);

    @POST("http://192.168.137.1:4000/register")
    Call<ResponseBody> toRegister(@Header("method") String head,@Body RequestBody requestBody);

    @POST("http://192.168.137.1:4000/register")
    Call<ResponseBody> toLogin(@Header("method") String head,@Body RequestBody requestBody);

}
