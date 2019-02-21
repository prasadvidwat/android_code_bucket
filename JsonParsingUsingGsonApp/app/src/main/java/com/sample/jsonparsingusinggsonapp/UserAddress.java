package com.sample.jsonparsingusinggsonapp;

class UserAddress {

    private String city;

    private String tel;

    private String dist;

    private int pin;

    public UserAddress() {
    }

    public UserAddress(String city, String tel, String dist, int pin) {
        this.city = city;
        this.tel = tel;
        this.dist = dist;
        this.pin = pin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "UserAddress {" +
                "city='" + city + '\'' +
                ", tel='" + tel + '\'' +
                ", dist='" + dist + '\'' +
                ", pin=" + pin +
                '}';
    }
}
