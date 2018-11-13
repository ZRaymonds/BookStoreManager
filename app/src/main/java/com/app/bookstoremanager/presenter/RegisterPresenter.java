package com.app.bookstoremanager.presenter;

import com.app.bookstoremanager.bean.UserBean;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.IRegisterModel;
import com.app.bookstoremanager.model.impl.RegisterModelImpl;
import com.app.bookstoremanager.view.IRegisterView;

public class RegisterPresenter {

    private IRegisterModel registerModel;

    private IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    public void setRegister() {
        registerModel.getRegister(registerView.getRgUsername(), registerView.getRgPassword(), new OnNetListener<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                int code = userBean.getCode();
                String msg = userBean.getMsg();
                registerView.showRegisterSuccess(code, msg);
            }

            @Override
            public void onFailure(Exception e) {
                registerView.showRegisterError(e);
            }
        });
    }

}
