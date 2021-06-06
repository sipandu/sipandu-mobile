package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatPemeriksaanLansiaDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("riwayat_pemeriksaan_lansia")
    @Expose
    private List<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansia = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<RiwayatPemeriksaanLansia> getRiwayatPemeriksaanLansia() {
        return riwayatPemeriksaanLansia;
    }

    public void setRiwayatPemeriksaanLansia(List<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansia) {
        this.riwayatPemeriksaanLansia = riwayatPemeriksaanLansia;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
