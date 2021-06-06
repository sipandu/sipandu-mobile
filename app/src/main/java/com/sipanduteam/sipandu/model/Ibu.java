package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ibu {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("NIK")
    @Expose
    private String nik;
    @SerializedName("nama_ibu_hamil")
    @Expose
    private String namaIbuHamil;
    @SerializedName("nama_suami")
    @Expose
    private Object namaSuami;
    @SerializedName("tempat_lahir")
    @Expose
    private Object tempatLahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private Object tanggalLahir;
    @SerializedName("kehamilan_ke")
    @Expose
    private Object kehamilanKe;
    @SerializedName("jarak_anak_sebelumnya")
    @Expose
    private Object jarakAnakSebelumnya;
    @SerializedName("pekerjaan_ibu")
    @Expose
    private Object pekerjaanIbu;
    @SerializedName("pekerjaan_suami")
    @Expose
    private Object pekerjaanSuami;
    @SerializedName("pendidikan_ibu")
    @Expose
    private Object pendidikanIbu;
    @SerializedName("pendidikan_suami")
    @Expose
    private Object pendidikanSuami;
    @SerializedName("nomor_telepon")
    @Expose
    private Object nomorTelepon;
    @SerializedName("alamat")
    @Expose
    private Object alamat;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPosyandu() {
        return idPosyandu;
    }

    public void setIdPosyandu(Integer idPosyandu) {
        this.idPosyandu = idPosyandu;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaIbuHamil() {
        return namaIbuHamil;
    }

    public void setNamaIbuHamil(String namaIbuHamil) {
        this.namaIbuHamil = namaIbuHamil;
    }

    public Object getNamaSuami() {
        return namaSuami;
    }

    public void setNamaSuami(Object namaSuami) {
        this.namaSuami = namaSuami;
    }

    public Object getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(Object tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Object getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Object tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Object getKehamilanKe() {
        return kehamilanKe;
    }

    public void setKehamilanKe(Object kehamilanKe) {
        this.kehamilanKe = kehamilanKe;
    }

    public Object getJarakAnakSebelumnya() {
        return jarakAnakSebelumnya;
    }

    public void setJarakAnakSebelumnya(Object jarakAnakSebelumnya) {
        this.jarakAnakSebelumnya = jarakAnakSebelumnya;
    }

    public Object getPekerjaanIbu() {
        return pekerjaanIbu;
    }

    public void setPekerjaanIbu(Object pekerjaanIbu) {
        this.pekerjaanIbu = pekerjaanIbu;
    }

    public Object getPekerjaanSuami() {
        return pekerjaanSuami;
    }

    public void setPekerjaanSuami(Object pekerjaanSuami) {
        this.pekerjaanSuami = pekerjaanSuami;
    }

    public Object getPendidikanIbu() {
        return pendidikanIbu;
    }

    public void setPendidikanIbu(Object pendidikanIbu) {
        this.pendidikanIbu = pendidikanIbu;
    }

    public Object getPendidikanSuami() {
        return pendidikanSuami;
    }

    public void setPendidikanSuami(Object pendidikanSuami) {
        this.pendidikanSuami = pendidikanSuami;
    }

    public Object getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(Object nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public Object getAlamat() {
        return alamat;
    }

    public void setAlamat(Object alamat) {
        this.alamat = alamat;
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
}
