package com.sipanduteam.sipandu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Informasi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("judul_informasi")
    @Expose
    private String judulInformasi;
    @SerializedName("informasi")
    @Expose
    private String informasi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("like")
    @Expose
    private Object like;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("dilihat")
    @Expose
    private Integer dilihat;
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

    public String getJudulInformasi() {
        return judulInformasi;
    }

    public void setJudulInformasi(String judulInformasi) {
        this.judulInformasi = judulInformasi;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
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

    public Object getLike() {
        return like;
    }

    public void setLike(Object like) {
        this.like = like;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getDilihat() {
        return dilihat;
    }

    public void setDilihat(Integer dilihat) {
        this.dilihat = dilihat;
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
