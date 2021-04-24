package com.sipanduteam.sipandu.model.posyandu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengumuman {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_posyandu")
    @Expose
    private Integer idPosyandu;
    @SerializedName("judul_pengumuman")
    @Expose
    private String judulPengumuman;
    @SerializedName("pengumuman")
    @Expose
    private String pengumuman;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("foto")
    @Expose
    private String foto;

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

    public String getJudulPengumuman() {
        return judulPengumuman;
    }

    public void setJudulPengumuman(String judulPengumuman) {
        this.judulPengumuman = judulPengumuman;
    }

    public String getPengumuman() {
        return pengumuman;
    }

    public void setPengumuman(String pengumuman) {
        this.pengumuman = pengumuman;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
