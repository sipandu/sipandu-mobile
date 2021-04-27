package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InformasiResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("informasi")
    @Expose
    private List<Informasi> informasi = null;
    @SerializedName("informasi_populer")
    @Expose
    private List<Informasi> informasiPopuler = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<Informasi> getInformasi() {
        return informasi;
    }

    public void setInformasi(List<Informasi> informasi) {
        this.informasi = informasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Informasi> getInformasiPopuler() {
        return informasiPopuler;
    }

    public void setInformasiPopuler(List<Informasi> informasiPopuler) {
        this.informasiPopuler = informasiPopuler;
    }
}
