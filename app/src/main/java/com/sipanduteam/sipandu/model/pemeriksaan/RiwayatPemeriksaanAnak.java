package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatPemeriksaanAnak {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("id_pegawai")
    @Expose
    private Integer idPegawai;
    @SerializedName("id_anak")
    @Expose
    private Integer idAnak;
    @SerializedName("nama_posyandu")
    @Expose
    private String namaPosyandu;
    @SerializedName("nama_pemeriksa")
    @Expose
    private String namaPemeriksa;
    @SerializedName("nama_anak")
    @Expose
    private String namaAnak;
    @SerializedName("usia_anak")
    @Expose
    private Integer usiaAnak;
    @SerializedName("lingkar_kepala")
    @Expose
    private Integer lingkarKepala;
    @SerializedName("berat_badan")
    @Expose
    private Integer beratBadan;
    @SerializedName("tinggi_badan")
    @Expose
    private Integer tinggiBadan;
    @SerializedName("keluhan")
    @Expose
    private Object keluhan;
    @SerializedName("diagnosa")
    @Expose
    private String diagnosa;
    @SerializedName("pengobatan")
    @Expose
    private String pengobatan;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tempat_pemeriksaan")
    @Expose
    private String tempatPemeriksaan;
    @SerializedName("jenis_pemeriksaan")
    @Expose
    private String jenisPemeriksaan;
    @SerializedName("tanggal_pemeriksaan")
    @Expose
    private String tanggalPemeriksaan;
    @SerializedName("tanggal_kembali")
    @Expose
    private String tanggalKembali;
    @SerializedName("IMT")
    @Expose
    private Integer imt;
    @SerializedName("status_gizi")
    @Expose
    private String statusGizi;
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

    public Integer getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Integer idPegawai) {
        this.idPegawai = idPegawai;
    }

    public Integer getIdAnak() {
        return idAnak;
    }

    public void setIdAnak(Integer idAnak) {
        this.idAnak = idAnak;
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

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public Integer getUsiaAnak() {
        return usiaAnak;
    }

    public void setUsiaAnak(Integer usiaAnak) {
        this.usiaAnak = usiaAnak;
    }

    public Integer getLingkarKepala() {
        return lingkarKepala;
    }

    public void setLingkarKepala(Integer lingkarKepala) {
        this.lingkarKepala = lingkarKepala;
    }

    public Integer getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }

    public Integer getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(Integer tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public Object getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(Object keluhan) {
        this.keluhan = keluhan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(String pengobatan) {
        this.pengobatan = pengobatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTempatPemeriksaan() {
        return tempatPemeriksaan;
    }

    public void setTempatPemeriksaan(String tempatPemeriksaan) {
        this.tempatPemeriksaan = tempatPemeriksaan;
    }

    public String getJenisPemeriksaan() {
        return jenisPemeriksaan;
    }

    public void setJenisPemeriksaan(String jenisPemeriksaan) {
        this.jenisPemeriksaan = jenisPemeriksaan;
    }

    public String getTanggalPemeriksaan() {
        return tanggalPemeriksaan;
    }

    public void setTanggalPemeriksaan(String tanggalPemeriksaan) {
        this.tanggalPemeriksaan = tanggalPemeriksaan;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Integer getImt() {
        return imt;
    }

    public void setImt(Integer imt) {
        this.imt = imt;
    }

    public String getStatusGizi() {
        return statusGizi;
    }

    public void setStatusGizi(String statusGizi) {
        this.statusGizi = statusGizi;
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
