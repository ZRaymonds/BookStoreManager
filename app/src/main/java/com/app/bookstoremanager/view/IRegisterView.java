package com.app.bookstoremanager.view;

public interface IRegisterView {

    void showRegisterSuccess(int code, String msg);

    void showRegisterError(Exception e);

    //得到用户填写的信息
    String getRgUsername();

    String getRgPassword();

}
