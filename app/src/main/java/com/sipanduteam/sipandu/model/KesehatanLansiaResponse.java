package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanLansia;

import java.util.List;

public class KesehatanLansiaResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("nama_ibu")
    @Expose
    private String namaIbu;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("riwayat_pemeriksaan_lansia")
    @Expose
    private List<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansia = null;
    @SerializedName("jumlah_vitamin")
    @Expose
    private Integer jumlahVitamin;
    @SerializedName("jumlah_konsultasi")
    @Expose
    private Integer jumlahKonsultasi;
    @SerializedName("jumlah_imunisasi")
    @Expose
    private Integer jumlahImunisasi;
    @SerializedName("jumlah_pemeriksaan")
    @Expose
    private Integer jumlahPemeriksaan;
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

    public List<RiwayatPemeriksaanLansia> getRiwayatPemeriksaanLansia() {
        return riwayatPemeriksaanLansia;
    }

    public void setRiwayatPemeriksaanLansia(List<RiwayatPemeriksaanLansia> riwayatPemeriksaanLansia) {
        this.riwayatPemeriksaanLansia = riwayatPemeriksaanLansia;
    }

    public Integer getJumlahVitamin() {
        return jumlahVitamin;
    }

    public void setJumlahVitamin(Integer jumlahVitamin) {
        this.jumlahVitamin = jumlahVitamin;
    }

    public Integer getJumlahKonsultasi() {
        return jumlahKonsultasi;
    }

    public void setJumlahKonsultasi(Integer jumlahKonsultasi) {
        this.jumlahKonsultasi = jumlahKonsultasi;
    }

    public Integer getJumlahImunisasi() {
        return jumlahImunisasi;
    }

    public void setJumlahImunisasi(Integer jumlahImunisasi) {
        this.jumlahImunisasi = jumlahImunisasi;
    }

    public Integer getJumlahPemeriksaan() {
        return jumlahPemeriksaan;
    }

    public void setJumlahPemeriksaan(Integer jumlahPemeriksaan) {
        this.jumlahPemeriksaan = jumlahPemeriksaan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
