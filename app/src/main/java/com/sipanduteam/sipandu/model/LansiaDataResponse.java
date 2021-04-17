package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LansiaDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("lansia")
    @Expose
    private Lansia lansia;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Lansia getLansia() {
        return lansia;
    }

    public void setLansia(Lansia lansia) {
        this.lansia = lansia;
    }
}
