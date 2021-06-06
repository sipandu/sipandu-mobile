package com.sipanduteam.sipandu.model.imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImunisasiDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("riwayat_imunisasi")
    @Expose
    private List<RiwayatImunisasi> riwayatImunisasi = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<RiwayatImunisasi> getRiwayatImunisasi() {
        return riwayatImunisasi;
    }

    public void setRiwayatImunisasi(List<RiwayatImunisasi> riwayatImunisasi) {
        this.riwayatImunisasi = riwayatImunisasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
