package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.pemeriksaan.RiwayatPemeriksaanAnak;

import java.util.List;

public class KesehatanAnakResponse {
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("nama_anak")
    @Expose
    private String namaAnak;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("riwayat_pemeriksaan_anak")
    @Expose
    private List<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnak = null;
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
    @SerializedName("id_anak")
    @Expose
    private Integer idAnak;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<RiwayatPemeriksaanAnak> getRiwayatPemeriksaanAnak() {
        return riwayatPemeriksaanAnak;
    }

    public void setRiwayatPemeriksaanAnak(List<RiwayatPemeriksaanAnak> riwayatPemeriksaanAnak) {
        this.riwayatPemeriksaanAnak = riwayatPemeriksaanAnak;
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

    public Integer getIdAnak() {
        return idAnak;
    }

    public void setIdAnak(Integer idAnak) {
        this.idAnak = idAnak;
    }
}
