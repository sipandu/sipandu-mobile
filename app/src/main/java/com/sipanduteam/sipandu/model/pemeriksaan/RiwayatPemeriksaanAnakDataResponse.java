package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatPemeriksaanAnakDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("riwayat_pemeriksaan_anak")
    @Expose
    private List<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnak = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<RiwayatPemeriksaanAnak> getRiwayatPemeriksaanAnak() {
        return riwayatPemeriksaanAnak;
    }

    public void setRiwayatPemeriksaanAnak(List<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnak) {
        this.riwayatPemeriksaanAnak = riwayatPemeriksaanAnak;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
