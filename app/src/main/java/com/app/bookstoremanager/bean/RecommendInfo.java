package com.app.bookstoremanager.bean;

public class RecommendInfo {

    private String name;
    private String imageUrl;

    //这里注意做一个无参数的构造器
    public RecommendInfo() {
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
