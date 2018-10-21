package com.app.bookstoremanager.bean;

public class UserBean {

    private int code;

    private String msg;

    private int id;

    private String mobile_phone;

    private String password;

    public UserBean() {
    }

    public UserBean(String mobile_phone, String password) {
        this.mobile_phone = mobile_phone;
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", id=" + id +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
