package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeluargakuAnakResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("nama_anak")
    @Expose
    private String namaAnak;
    @SerializedName("pemeriksaan_anak_terakhir")
    @Expose
    private String pemeriksaanAnakTerakhir;
    @SerializedName("status_gizi_anak")
    @Expose
    private String statusGiziAnak;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("flag")
    @Expose
    private Integer flag;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public String getPemeriksaanAnakTerakhir() {
        return pemeriksaanAnakTerakhir;
    }

    public void setPemeriksaanAnakTerakhir(String pemeriksaanAnakTerakhir) {
        this.pemeriksaanAnakTerakhir = pemeriksaanAnakTerakhir;
    }

    public String getStatusGiziAnak() {
        return statusGiziAnak;
    }

    public void setStatusGiziAnak(String statusGiziAnak) {
        this.statusGiziAnak = statusGiziAnak;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
