package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenyakitBawaanDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("penyakit_bawaan")
    @Expose
    private List<PenyakitBawaan> penyakitBawaan = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<PenyakitBawaan> getPenyakitBawaan() {
        return penyakitBawaan;
    }

    public void setPenyakitBawaan(List<PenyakitBawaan> penyakitBawaan) {
        this.penyakitBawaan = penyakitBawaan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
