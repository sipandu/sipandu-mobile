package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasalahKesehatanLansia {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_lansia")
    @Expose
    private Integer idLansia;
    @SerializedName("nama_penyakit")
    @Expose
    private String namaPenyakit;
    @SerializedName("status")
    @Expose
    private String status;
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

    public Integer getIdLansia() {
        return idLansia;
    }

    public void setIdLansia(Integer idLansia) {
        this.idLansia = idLansia;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
