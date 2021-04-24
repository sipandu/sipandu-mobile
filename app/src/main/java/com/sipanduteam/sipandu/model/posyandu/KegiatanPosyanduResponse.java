package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KegiatanPosyanduResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("kegiatan")
    @Expose
    private List<Kegiatan> kegiatan = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Kegiatan> getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(List<Kegiatan> kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
