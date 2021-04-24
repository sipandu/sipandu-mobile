package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PegawaiResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("pegawai")
    @Expose
    private List<Pegawai> pegawai = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Pegawai> getPegawai() {
        return pegawai;
    }

    public void setPegawai(List<Pegawai> pegawai) {
        this.pegawai = pegawai;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
