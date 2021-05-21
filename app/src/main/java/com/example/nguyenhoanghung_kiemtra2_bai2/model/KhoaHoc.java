package com.example.nguyenhoanghung_kiemtra2_bai2.model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private int id;
    private String ten;
    private String ngay;
    private String chuyenNganh;
    private int kichhoat;

    public KhoaHoc() {
    }

    public KhoaHoc(int id, String ten, String ngay, String chuyenNganh, int kichhoat) {
        this.id = id;
        this.ten = ten;
        this.ngay = ngay;
        this.chuyenNganh = chuyenNganh;
        this.kichhoat = kichhoat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public int getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(int kichhoat) {
        this.kichhoat = kichhoat;
    }
}
