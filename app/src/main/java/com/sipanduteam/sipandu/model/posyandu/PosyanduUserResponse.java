package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosyanduUserResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("posyandu")
    @Expose
    private Posyandu posyandu;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Posyandu getPosyandu() {
        return posyandu;
    }

    public void setPosyandu(Posyandu posyandu) {
        this.posyandu = posyandu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
