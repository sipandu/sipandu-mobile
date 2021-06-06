package com.sipanduteam.sipandu.model.vitamin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VitaminDataResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("riwayat_vitamin")
    @Expose
    private List<RiwayatVitamin> riwayatVitamin = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<RiwayatVitamin> getRiwayatVitamin() {
        return riwayatVitamin;
    }

    public void setRiwayatVitamin(List<RiwayatVitamin> riwayatVitamin) {
        this.riwayatVitamin = riwayatVitamin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
