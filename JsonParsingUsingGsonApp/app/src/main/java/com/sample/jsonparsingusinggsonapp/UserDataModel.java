package com.sample.jsonparsingusinggsonapp;

import java.util.List;

public class UserDataModel {

    private List<UserModel> userdata;

    public UserDataModel() {
    }

    public UserDataModel(List<UserModel> userdata) {
        this.userdata = userdata;
    }

    public List<UserModel> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<UserModel> userdata) {
        this.userdata = userdata;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("UserDataModel = \n");

        if(userdata != null && userdata.size() > 0)
        {
            for(int i=0; i<userdata.size(); i++)
            {
                builder.append("\t"+userdata.get(i));
                builder.append("\n--------------\n");
            }
        }

        return new String(builder);
    }
}
