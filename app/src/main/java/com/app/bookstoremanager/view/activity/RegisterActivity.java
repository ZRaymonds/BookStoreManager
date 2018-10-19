package com.app.bookstoremanager.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.bookstoremanager.R;
import com.app.bookstoremanager.presenter.RegisterPresenter;
import com.app.bookstoremanager.utils.LogUtil;
import com.app.bookstoremanager.utils.ToastUtil;
import com.app.bookstoremanager.utils.VerifyUtil;
import com.app.bookstoremanager.view.IRegisterView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    @ViewInject(R.id.et_register_username)
    EditText et_register_username;

    @ViewInject(R.id.et_register_password)
    EditText et_register_password;

    @ViewInject(R.id.btn_register)
    Button btn_register;

    @ViewInject(R.id.back)
    ImageView back;

    private RegisterPresenter registerPresenter;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mContext = this;
        registerPresenter = new RegisterPresenter(this);
    }

    @Event({R.id.btn_register,R.id.back})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if (VerifyUtil.isConnect(mContext)) {
                    boolean b = isPhoneNumber(getRgUsername());
                    if (getRgUsername().isEmpty() || getRgPassword().isEmpty()) {
                        Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    } else if (!b) {
                        Toast.makeText(this, "输入的手机号有误", Toast.LENGTH_SHORT).show();
                    } else if (getRgPassword().length() < 6) {
                        Toast.makeText(this, "密码不能少于六位数", Toast.LENGTH_SHORT).show();
                    } else {
                        registerPresenter.setRegister();
                    }
                } else {
                    ToastUtil.show(this, "请检查网络设置");
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void showRegisterSuccess(int code, String msg) {
        if (code == 200){
            ToastUtil.show(this, msg);
            finish();
        }else {
            ToastUtil.show(this, msg);
        }
    }

    @Override
    public void showRegisterError(Exception e) {
        ToastUtil.show(this, e.toString());
        LogUtil.d("TAG", e.toString());
    }

    @Override
    public String getRgUsername() {
        return et_register_username.getText().toString();
    }

    @Override
    public String getRgPassword() {
        return et_register_password.getText().toString();
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

}
