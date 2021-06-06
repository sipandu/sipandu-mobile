package com.sipanduteam.sipandu.model.imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatImunisasi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_jenis_imunisasi")
    @Expose
    private Integer idJenisImunisasi;
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
    @SerializedName("tanggal_imunisasi")
    @Expose
    private String tanggalImunisasi;
    @SerializedName("tanggal_kembali")
    @Expose
    private String tanggalKembali;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("imunisasi")
    @Expose
    private Imunisasi imunisasi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdJenisImunisasi() {
        return idJenisImunisasi;
    }

    public void setIdJenisImunisasi(Integer idJenisImunisasi) {
        this.idJenisImunisasi = idJenisImunisasi;
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

    public String getTanggalImunisasi() {
        return tanggalImunisasi;
    }

    public void setTanggalImunisasi(String tanggalImunisasi) {
        this.tanggalImunisasi = tanggalImunisasi;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
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

    public Imunisasi getImunisasi() {
        return imunisasi;
    }

    public void setImunisasi(Imunisasi imunisasi) {
        this.imunisasi = imunisasi;
    }
}
