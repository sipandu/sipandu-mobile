package com.sipanduteam.sipandu.model.imunisasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imunisasi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_imunisasi")
    @Expose
    private String namaImunisasi;
    @SerializedName("usia_pemberian")
    @Expose
    private String usiaPemberian;
    @SerializedName("perulangan")
    @Expose
    private String perulangan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("penerima")
    @Expose
    private String penerima;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
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

    public String getNamaImunisasi() {
        return namaImunisasi;
    }

    public void setNamaImunisasi(String namaImunisasi) {
        this.namaImunisasi = namaImunisasi;
    }

    public String getUsiaPemberian() {
        return usiaPemberian;
    }

    public void setUsiaPemberian(String usiaPemberian) {
        this.usiaPemberian = usiaPemberian;
    }

    public String getPerulangan() {
        return perulangan;
    }

    public void setPerulangan(String perulangan) {
        this.perulangan = perulangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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
