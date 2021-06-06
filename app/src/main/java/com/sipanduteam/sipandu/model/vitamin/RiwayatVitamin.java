package com.sipanduteam.sipandu.model.vitamin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatVitamin {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_jenis_vitamin")
    @Expose
    private Integer idJenisVitamin;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_pegawai")
    @Expose
    private Integer idPegawai;
    @SerializedName("nama_posyandu")
    @Expose
    private String namaPosyandu;
    @SerializedName("nama_pemeriksa")
    @Expose
    private String namaPemeriksa;
    @SerializedName("usia")
    @Expose
    private Integer usia;
    @SerializedName("tanggal_pemberian")
    @Expose
    private String tanggalPemberian;
    @SerializedName("tanggal_kembali")
    @Expose
    private Object tanggalKembali;
    @SerializedName("keterangan")
    @Expose
    private Object keterangan;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("vitamin")
    @Expose
    private Vitamin vitamin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdJenisVitamin() {
        return idJenisVitamin;
    }

    public void setIdJenisVitamin(Integer idJenisVitamin) {
        this.idJenisVitamin = idJenisVitamin;
    }

    public Integer getIdPosyandu() {
        return idPosyandu;
    }

    public void setIdPosyandu(Integer idPosyandu) {
        this.idPosyandu = idPosyandu;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Integer idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNamaPosyandu() {
        return namaPosyandu;
    }

    public void setNamaPosyandu(String namaPosyandu) {
        this.namaPosyandu = namaPosyandu;
    }

    public String getNamaPemeriksa() {
        return namaPemeriksa;
    }

    public void setNamaPemeriksa(String namaPemeriksa) {
        this.namaPemeriksa = namaPemeriksa;
    }

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }

    public String getTanggalPemberian() {
        return tanggalPemberian;
    }

    public void setTanggalPemberian(String tanggalPemberian) {
        this.tanggalPemberian = tanggalPemberian;
    }

    public Object getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Object tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Object getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(Object keterangan) {
        this.keterangan = keterangan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Vitamin getVitamin() {
        return vitamin;
    }

    public void setVitamin(Vitamin vitamin) {
        this.vitamin = vitamin;
    }
}
