package com.example.baitap7.model;

public class SinhVien {
    private String id, name;
    private float diemtb;

    public SinhVien() {
    }

    public SinhVien(String id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.diemtb = diemtb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiemtb() {
        return diemtb;
    }

    public void setDiemtb(float salary) {
        this.diemtb = salary;
    }
}
