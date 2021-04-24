package com.sipanduteam.sipandu.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sipanduteam.sipandu.model.posyandu.Posyandu;

public class Desa {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kecamatan")
    @Expose
    private Integer idKecamatan;
    @SerializedName("nama_desa")
    @Expose
    private String namaDesa;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("posyandu")
    @Expose
    private List<Posyandu> posyandu = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(Integer idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
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

    public List<Posyandu> getPosyandu() {
        return posyandu;
    }

    public void setPosyandu(List<Posyandu> posyandu) {
        this.posyandu = posyandu;
    }
}
