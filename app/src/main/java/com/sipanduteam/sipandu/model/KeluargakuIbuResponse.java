package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeluargakuIbuResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("nama_ibu")
    @Expose
    private String namaIbu;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("pemeriksaan_ibu_terakhir")
    @Expose
    private String pemeriksaanIbuTerakhir;
    @SerializedName("usia_kandungan")
    @Expose
    private Integer usiaKandungan;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getPemeriksaanIbuTerakhir() {
        return pemeriksaanIbuTerakhir;
    }

    public void setPemeriksaanIbuTerakhir(String pemeriksaanIbuTerakhir) {
        this.pemeriksaanIbuTerakhir = pemeriksaanIbuTerakhir;
    }

    public Integer getUsiaKandungan() {
        return usiaKandungan;
    }

    public void setUsiaKandungan(Integer usiaKandungan) {
        this.usiaKandungan = usiaKandungan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
