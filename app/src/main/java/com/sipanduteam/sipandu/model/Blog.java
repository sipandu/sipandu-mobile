package com.sipanduteam.sipandu.model;

public class Blog {
    String title, keterangan;

    public Blog(String title, String keterangan) {
        this.title = title;
        this.keterangan = keterangan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
