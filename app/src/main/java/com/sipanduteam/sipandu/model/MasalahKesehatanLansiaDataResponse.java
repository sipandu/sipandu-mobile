package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasalahKesehatanLansiaDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("masalah_kesehatan_lansia")
    @Expose
    private List<MasalahKesehatanLansia> masalahKesehatanLansia = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<MasalahKesehatanLansia> getMasalahKesehatanLansia() {
        return masalahKesehatanLansia;
    }

    public void setMasalahKesehatanLansia(List<MasalahKesehatanLansia> masalahKesehatanLansia) {
        this.masalahKesehatanLansia = masalahKesehatanLansia;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
