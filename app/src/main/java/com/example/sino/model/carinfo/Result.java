
package com.example.sino.model.carinfo;

import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("car")
    @Expose
    public Car car;

    @SerializedName("user")
    @Expose
    public AppUser user;

}
