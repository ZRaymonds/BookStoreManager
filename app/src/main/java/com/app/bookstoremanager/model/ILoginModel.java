package com.app.bookstoremanager.model;

import com.app.bookstoremanager.bean.UserBean;
import com.app.bookstoremanager.callBack.OnNetListener;

public interface ILoginModel {

    void getLogin(String mobile_phone, String password, final OnNetListener<UserBean> onNetListener);

}
