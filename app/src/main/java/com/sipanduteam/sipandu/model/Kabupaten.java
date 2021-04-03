package com.sipanduteam.sipandu.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kabupaten {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_kabupaten")
    @Expose
    private String namaKabupaten;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("kecamatan")
    @Expose
    private List<Kecamatan> kecamatan = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaKabupaten() {
        return namaKabupaten;
    }

    public void setNamaKabupaten(String namaKabupaten) {
        this.namaKabupaten = namaKabupaten;
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

    public List<Kecamatan> getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(List<Kecamatan> kecamatan) {
        this.kecamatan = kecamatan;
    }
}
