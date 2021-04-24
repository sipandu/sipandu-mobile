package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengumumanResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("pengumuman")
    @Expose
    private List<Pengumuman> pengumuman = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Pengumuman> getPengumuman() {
        return pengumuman;
    }

    public void setPengumuman(List<Pengumuman> pengumuman) {
        this.pengumuman = pengumuman;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
