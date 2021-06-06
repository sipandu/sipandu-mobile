package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatPemeriksaanLansia {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("id_pegawai")
    @Expose
    private Integer idPegawai;
    @SerializedName("id_lansia")
    @Expose
    private Integer idLansia;
    @SerializedName("nama_posyandu")
    @Expose
    private String namaPosyandu;
    @SerializedName("nama_pemeriksa")
    @Expose
    private String namaPemeriksa;
    @SerializedName("nama_lansia")
    @Expose
    private String namaLansia;
    @SerializedName("usia_lansia")
    @Expose
    private Integer usiaLansia;
    @SerializedName("berat_badan")
    @Expose
    private Integer beratBadan;
    @SerializedName("suhu_tubuh")
    @Expose
    private Integer suhuTubuh;
    @SerializedName("tinggi_lutut")
    @Expose
    private Integer tinggiLutut;
    @SerializedName("tinggi_badan")
    @Expose
    private Integer tinggiBadan;
    @SerializedName("tekanan_darah")
    @Expose
    private String tekananDarah;
    @SerializedName("denyut_nadi")
    @Expose
    private Integer denyutNadi;
    @SerializedName("keluhan")
    @Expose
    private Object keluhan;
    @SerializedName("diagnosa")
    @Expose
    private String diagnosa;
    @SerializedName("pengobatan")
    @Expose
    private Object pengobatan;
    @SerializedName("keterangan")
    @Expose
    private Object keterangan;
    @SerializedName("tempat_pemeriksaan")
    @Expose
    private String tempatPemeriksaan;
    @SerializedName("jenis_pemeriksaan")
    @Expose
    private String jenisPemeriksaan;
    @SerializedName("IMT")
    @Expose
    private Double imt;
    @SerializedName("tanggal_pemeriksaan")
    @Expose
    private String tanggalPemeriksaan;
    @SerializedName("tanggal_kembali")
    @Expose
    private String tanggalKembali;
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

    public Integer getIdLansia() {
        return idLansia;
    }

    public void setIdLansia(Integer idLansia) {
        this.idLansia = idLansia;
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

    public String getNamaLansia() {
        return namaLansia;
    }

    public void setNamaLansia(String namaLansia) {
        this.namaLansia = namaLansia;
    }

    public Integer getUsiaLansia() {
        return usiaLansia;
    }

    public void setUsiaLansia(Integer usiaLansia) {
        this.usiaLansia = usiaLansia;
    }

    public Integer getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }

    public Integer getSuhuTubuh() {
        return suhuTubuh;
    }

    public void setSuhuTubuh(Integer suhuTubuh) {
        this.suhuTubuh = suhuTubuh;
    }

    public Integer getTinggiLutut() {
        return tinggiLutut;
    }

    public void setTinggiLutut(Integer tinggiLutut) {
        this.tinggiLutut = tinggiLutut;
    }

    public Integer getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(Integer tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public String getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(String tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public Integer getDenyutNadi() {
        return denyutNadi;
    }

    public void setDenyutNadi(Integer denyutNadi) {
        this.denyutNadi = denyutNadi;
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

    public Object getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(Object pengobatan) {
        this.pengobatan = pengobatan;
    }

    public Object getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(Object keterangan) {
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

    public Double getImt() {
        return imt;
    }

    public void setImt(Double imt) {
        this.imt = imt;
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
