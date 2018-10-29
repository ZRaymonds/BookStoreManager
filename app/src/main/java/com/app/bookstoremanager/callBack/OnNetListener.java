package com.app.bookstoremanager.callBack;

public interface OnNetListener<T> {

    void onSuccess(T t);

    void onFailure(Exception e);

}
