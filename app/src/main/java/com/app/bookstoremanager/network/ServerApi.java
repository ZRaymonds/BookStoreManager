package com.app.bookstoremanager.network;

import com.app.bookstoremanager.bean.UpdateInfo;
import com.app.bookstoremanager.bean.UserBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface ServerApi {

    @FormUrlEncoded
    @POST(Api.REGISTR)
    Observable<UserBean> register(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Api.LOGIN)
    Observable<UserBean> login(@FieldMap Map<String, String> map);

    @GET(Api.UPDATE)
    Observable<UpdateInfo> getUpdateInfo();

}
