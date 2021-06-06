package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatPemeriksaanIbuDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("riwayat_pemeriksaan_ibu")
    @Expose
    private List<RiwayatPemeriksaanIbu> riwayatPemeriksaanIbu = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<RiwayatPemeriksaanIbu> getRiwayatPemeriksaanIbu() {
        return riwayatPemeriksaanIbu;
    }

    public void setRiwayatPemeriksaanIbu(List<RiwayatPemeriksaanIbu> riwayatPemeriksaanIbu) {
        this.riwayatPemeriksaanIbu = riwayatPemeriksaanIbu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
