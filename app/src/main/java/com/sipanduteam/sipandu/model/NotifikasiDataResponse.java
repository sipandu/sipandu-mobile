package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotifikasiDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("notifikasi")
    @Expose
    private List<Notifikasi> notifikasi = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("flag")
    @Expose
    private Integer flag;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Notifikasi> getNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(List<Notifikasi> notifikasi) {
        this.notifikasi = notifikasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
