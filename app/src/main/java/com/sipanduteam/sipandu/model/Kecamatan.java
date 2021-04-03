package com.sipanduteam.sipandu.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kecamatan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kabupaten")
    @Expose
    private Integer idKabupaten;
    @SerializedName("nama_kecamatan")
    @Expose
    private String namaKecamatan;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("desa")
    @Expose
    private List<Desa> desa = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKabupaten() {
        return idKabupaten;
    }

    public void setIdKabupaten(Integer idKabupaten) {
        this.idKabupaten = idKabupaten;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
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

    public List<Desa> getDesa() {
        return desa;
    }

    public void setDesa(List<Desa> desa) {
        this.desa = desa;
    }
}
