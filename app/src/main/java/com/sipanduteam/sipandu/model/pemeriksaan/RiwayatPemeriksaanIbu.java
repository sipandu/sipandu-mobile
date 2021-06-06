package com.sipanduteam.sipandu.model.pemeriksaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatPemeriksaanIbu {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("id_ibu_hamil")
    @Expose
    private Integer idIbuHamil;
    @SerializedName("id_pegawai")
    @Expose
    private Integer idPegawai;
    @SerializedName("nama_posyandu")
    @Expose
    private String namaPosyandu;
    @SerializedName("nama_pemeriksa")
    @Expose
    private String namaPemeriksa;
    @SerializedName("nama_ibu_hamil")
    @Expose
    private String namaIbuHamil;
    @SerializedName("lingkar_lengan")
    @Expose
    private Integer lingkarLengan;
    @SerializedName("berat_badan")
    @Expose
    private Integer beratBadan;
    @SerializedName("usia_kandungan")
    @Expose
    private Integer usiaKandungan;
    @SerializedName("tekanan_darah")
    @Expose
    private String tekananDarah;
    @SerializedName("denyut_nadi_ibu")
    @Expose
    private Integer denyutNadiIbu;
    @SerializedName("detak_jantung_bayi")
    @Expose
    private Integer detakJantungBayi;
    @SerializedName("tinggi_rahim")
    @Expose
    private Integer tinggiRahim;
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

    public Integer getIdIbuHamil() {
        return idIbuHamil;
    }

    public void setIdIbuHamil(Integer idIbuHamil) {
        this.idIbuHamil = idIbuHamil;
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

    public String getNamaIbuHamil() {
        return namaIbuHamil;
    }

    public void setNamaIbuHamil(String namaIbuHamil) {
        this.namaIbuHamil = namaIbuHamil;
    }

    public Integer getLingkarLengan() {
        return lingkarLengan;
    }

    public void setLingkarLengan(Integer lingkarLengan) {
        this.lingkarLengan = lingkarLengan;
    }

    public Integer getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(Integer beratBadan) {
        this.beratBadan = beratBadan;
    }

    public Integer getUsiaKandungan() {
        return usiaKandungan;
    }

    public void setUsiaKandungan(Integer usiaKandungan) {
        this.usiaKandungan = usiaKandungan;
    }

    public String getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(String tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public Integer getDenyutNadiIbu() {
        return denyutNadiIbu;
    }

    public void setDenyutNadiIbu(Integer denyutNadiIbu) {
        this.denyutNadiIbu = denyutNadiIbu;
    }

    public Integer getDetakJantungBayi() {
        return detakJantungBayi;
    }

    public void setDetakJantungBayi(Integer detakJantungBayi) {
        this.detakJantungBayi = detakJantungBayi;
    }

    public Integer getTinggiRahim() {
        return tinggiRahim;
    }

    public void setTinggiRahim(Integer tinggiRahim) {
        this.tinggiRahim = tinggiRahim;
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
