package com.example.baitap7.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baitap7.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
    private SQLiteDatabase db;

    public SinhVienDao(Context context) {
        com.example.baitap7.sqlite.DBHelper helper = new com.example.baitap7.sqlite.DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<SinhVien> get(String sql , String ...selectArgs){
        List<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while(cursor.moveToNext()){
            SinhVien sv = new SinhVien();
            sv.setId(((Cursor) cursor).getString(cursor.getColumnIndex("id")));
            sv.setName(cursor.getString(cursor.getColumnIndex("name")));
            sv.setDiemtb(cursor.getFloat(cursor.getColumnIndex("diemtb")));
            list.add(sv);
        }
        return list;
    }

    public List<SinhVien> getAll(){
        String sql = "SELECT * FROM sinhvien";
        return get(sql);
    }

    public SinhVien getById(String id){
        String sql = "SELECT *FROM sinhvien WHERE id = ?";
        List<SinhVien> list = get(sql, id);
        return list.get(0);
    }

    public long insert(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("id", sv.getId());
        values.put("name", sv.getName());
        values.put("diemtb", sv.getDiemtb());
        return db.insert("sinhvien", null, values);
    }

    public long update(SinhVien sv){
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("diemtb", sv.getDiemtb());
        return db.update("sinhvien", values, "id=?", new String[]{sv.getId()});
    }

    public int delete(String id){
        return db.delete("nhanvien", "id=?", new String[]{id});
    }
}
