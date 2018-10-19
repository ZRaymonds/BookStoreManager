package com.app.bookstoremanager.presenter;

import com.app.bookstoremanager.bean.UserBean;
import com.app.bookstoremanager.callBack.OnNetListener;
import com.app.bookstoremanager.model.ILoginModel;
import com.app.bookstoremanager.model.impl.LoginModelImpl;
import com.app.bookstoremanager.view.ILoginView;

public class LoginPresenter {

    private ILoginModel iLoginModel;

    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }

    public void setLogin() {
        iLoginView.showLoading();
        iLoginModel.getLogin(iLoginView.getLgUsername(), iLoginView.getLgPassword(), new OnNetListener<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                int code = userBean.getCode();
                String msg = userBean.getMsg();
                iLoginView.showLoginSuccess(code, msg);
                iLoginView.hideLoading();
            }

            @Override
            public void onFailure(Exception e) {
                iLoginView.showLoginError(e);
                iLoginView.hideLoading();
            }
        });
    }

}
