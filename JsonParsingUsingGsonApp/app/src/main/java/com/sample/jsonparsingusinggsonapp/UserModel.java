package com.sample.jsonparsingusinggsonapp;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    private int userId;

    private String name;

    private String phone;

    private BirthDate bday;

    private UserAddress address;

    public UserModel() {
    }

    public UserModel(int userId, String name, String phone, BirthDate bday, UserAddress address) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BirthDate getBday() {
        return bday;
    }

    public void setBday(BirthDate bday) {
        this.bday = bday;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserModel {" + "\n"+
                "userId=" + userId + "\n"+
                ", name='" + name + '\'' + "\n"+
                ", phone='" + phone + '\'' + "\n"+
                ", bday=" + bday + "\n"+
                ", address=" + address + "\n"+
                '}';
    }
}
