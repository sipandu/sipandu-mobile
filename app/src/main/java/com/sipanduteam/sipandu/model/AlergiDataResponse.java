package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlergiDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("alergi")
    @Expose
    private List<Alergi> alergi = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Alergi> getAlergi() {
        return alergi;
    }

    public void setAlergi(List<Alergi> alergi) {
        this.alergi = alergi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
