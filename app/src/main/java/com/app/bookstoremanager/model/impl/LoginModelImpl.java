package com.app.bookstoremanager.model.impl;

import com.app.bookstoremanager.bean.UserBean;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.ILoginModel;
import com.app.bookstoremanager.network.ServerApi;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModelImpl implements ILoginModel {

    Map<String, String> map = new HashMap<>();

    @Override
    public void getLogin(String mobile_phone, String password, final OnNetListener<UserBean> onNetListener) {
        map.put("mobile_phone", mobile_phone);
        map.put("password", password);
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.login(map)
                .subscribeOn(Schedulers.io()) //请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread()) //请求完成后在主线程更显UI
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() { //所有事件都完成，可以做些操作。。。

                    }

                    @Override
                    public void onError(Throwable e) { //请求过程中发生错误
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(UserBean userBean) { //这里的userBean就是我们请求接口返回的实体类
                        onNetListener.onSuccess(userBean);
                        LogUtil.e("TAG",userBean.toString());
                    }
                });
    }
}
