package com.app.bookstoremanager.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.base.BaseActivity;
import com.app.bookstoremanager.presenter.LoginPresenter;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.utils.ToastUtil;
import com.app.bookstoremanager.utils.VerifyUtil;
import com.app.bookstoremanager.view.ILoginView;
import com.app.bookstoremanager.widget.LoadingDialog;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.LayoutName)
    TextInputLayout LayoutName;

    @BindView(R.id.LayoutPassword)
    TextInputLayout LayoutPassword;

    @BindView(R.id.et_login_username)
    EditText et_login_username;

    @BindView(R.id.et_login_password)
    EditText et_login_password;

    @BindView(R.id.tv_userRegister)
    TextView tv_userRegister;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.btn_login)
    Button btn_login;

    private Context mContext;

    private LoadingDialog loadingDialog;

    private LoginPresenter loginPresenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mContext = this;
        loginPresenter = new LoginPresenter(this);
        initLoadDialog();
        onEffectName(getLgUsername());
        onEffectPassword(getLgPassword());
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_userRegister, R.id.back, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_userRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_login:
                if (VerifyUtil.isConnect(mContext)) {
                    if (validateAccount(getLgUsername()) && validatePassword(getLgPassword())) {
                        loginPresenter.setLogin();
                    }
                } else {
                    ToastUtil.show(this, "请检查网络设置");
                }
                break;
        }
    }

    @Override
    public void showLoginSuccess(int code, String msg) {
        if (code == 200) {
            ToastUtil.show(this, msg);
            finish();
        } else {
            ToastUtil.show(this, msg);
        }
    }

    @Override
    public void showLoginError(Exception e) {
        ToastUtil.show(this, e.toString());
        LogUtil.d("TAG", e.toString());
    }

    @Override
    public String getLgUsername() {
        return et_login_username.getText().toString();
    }

    @Override
    public String getLgPassword() {
        return et_login_password.getText().toString();
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param mobile
     * @return
     */
    private boolean validateAccount(String mobile) {
        if (mobile.isEmpty()) {
            showError(LayoutName, "手机号不能为空");
            return false;
        } else {
            LayoutName.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            showError(LayoutPassword, "密码不能为空");
            return false;
        } else {
            LayoutPassword.setErrorEnabled(false);
        }
        return true;
    }


    private void onEffectName(final String mobile) {
        LayoutName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LayoutName.setError("请输入正确的手机号");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean b = isPhoneNumber(mobile);
                if (charSequence.length() < 11 && !b) {
                    LayoutName.setError("请输入正确的手机号");
                    LayoutName.setErrorEnabled(true);
                } else {
                    LayoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void onEffectPassword(String password) {
        LayoutPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 6 || charSequence.length() > 18) {
                    LayoutPassword.setError("密码长度为6-18位");
                    LayoutPassword.setErrorEnabled(true);
                } else {
                    LayoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean isPhoneNumber(String phoneStr) {
        //定义电话格式的正则表达式
        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //设定查看模式
        Pattern p = Pattern.compile(regex);
        //判断Str是否匹配，返回匹配结果
        Matcher m = p.matcher(phoneStr);
        return m.find();
    }

    /**
     * 初始化 LoadDialog
     */
    public void initLoadDialog() {
        loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
        // 不能自己取消
        loadingDialog.setCancelable(false);
        loadingDialog.initDialog("登陆中");
    }
}
