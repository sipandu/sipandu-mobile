package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeluargakuLansiaResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("nama_lansia")
    @Expose
    private String namaLansia;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("pemeriksaan_lansia_terakhir")
    @Expose
    private String pemeriksaanLansiaTerakhir;
    @SerializedName("imt")
    @Expose
    private Double imt;
    @SerializedName("status_lansia")
    @Expose
    private String statusLansia;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getNamaLansia() {
        return namaLansia;
    }

    public void setNamaLansia(String namaLansia) {
        this.namaLansia = namaLansia;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getPemeriksaanLansiaTerakhir() {
        return pemeriksaanLansiaTerakhir;
    }

    public void setPemeriksaanLansiaTerakhir(String pemeriksaanLansiaTerakhir) {
        this.pemeriksaanLansiaTerakhir = pemeriksaanLansiaTerakhir;
    }

    public Double getImt() {
        return imt;
    }

    public void setImt(Double imt) {
        this.imt = imt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusLansia() {
        return statusLansia;
    }

    public void setStatusLansia(String statusLansia) {
        this.statusLansia = statusLansia;
    }
}
