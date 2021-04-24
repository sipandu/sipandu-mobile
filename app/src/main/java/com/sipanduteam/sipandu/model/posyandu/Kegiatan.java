package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kegiatan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("nama_kegiatan")
    @Expose
    private String namaKegiatan;
    @SerializedName("tempat")
    @Expose
    private String tempat;
    @SerializedName("start_at")
    @Expose
    private String startAt;
    @SerializedName("end_at")
    @Expose
    private String endAt;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("like")
    @Expose
    private Object like;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;

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

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Object getLike() {
        return like;
    }

    public void setLike(Object like) {
        this.like = like;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
