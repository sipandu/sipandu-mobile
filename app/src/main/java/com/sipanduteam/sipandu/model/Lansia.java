package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lansia {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("NIK")
    @Expose
    private String nik;
    @SerializedName("nama_lansia")
    @Expose
    private String namaLansia;
    @SerializedName("tempat_lahir")
    @Expose
    private String tempatLahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("status_perkawinan")
    @Expose
    private String statusPerkawinan;
    @SerializedName("pendidikan_terakhir")
    @Expose
    private String pendidikanTerakhir;
    @SerializedName("sumber_biaya_hidup")
    @Expose
    private String sumberBiayaHidup;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jumlah_anak")
    @Expose
    private Integer jumlahAnak;
    @SerializedName("jumlah_cucu")
    @Expose
    private Integer jumlahCucu;
    @SerializedName("jumlah_cicit")
    @Expose
    private Integer jumlahCicit;
    @SerializedName("jumlah_keluarga_serumah")
    @Expose
    private Object jumlahKeluargaSerumah;
    @SerializedName("nomor_telepon")
    @Expose
    private String nomorTelepon;
    @SerializedName("tempat_tinggal")
    @Expose
    private String tempatTinggal;
    @SerializedName("alamat")
    @Expose
    private String alamat;
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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLansia() {
        return namaLansia;
    }

    public void setNamaLansia(String namaLansia) {
        this.namaLansia = namaLansia;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getSumberBiayaHidup() {
        return sumberBiayaHidup;
    }

    public void setSumberBiayaHidup(String sumberBiayaHidup) {
        this.sumberBiayaHidup = sumberBiayaHidup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public Integer getJumlahCucu() {
        return jumlahCucu;
    }

    public void setJumlahCucu(Integer jumlahCucu) {
        this.jumlahCucu = jumlahCucu;
    }

    public Integer getJumlahCicit() {
        return jumlahCicit;
    }

    public void setJumlahCicit(Integer jumlahCicit) {
        this.jumlahCicit = jumlahCicit;
    }

    public Object getJumlahKeluargaSerumah() {
        return jumlahKeluargaSerumah;
    }

    public void setJumlahKeluargaSerumah(Object jumlahKeluargaSerumah) {
        this.jumlahKeluargaSerumah = jumlahKeluargaSerumah;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getTempatTinggal() {
        return tempatTinggal;
    }

    public void setTempatTinggal(String tempatTinggal) {
        this.tempatTinggal = tempatTinggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
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
