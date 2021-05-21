package com.example.nguyenhoanghung_kiemtra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.nguyenhoanghung_kiemtra2_bai2.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class SQLiteKhoaHocHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "hoconline.db";
    private static final int DB_VERSION = 1;

    public SQLiteKhoaHocHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE khoahoc(\n" +
                "\t id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  \t ten TEXT,\n" +
                "  ngay TEXT,\n" +
                "  chuyennganh TEXT,\n" +
                "  kichhoat INTEGER\n" +
                ")";
        db.execSQL(sql);
    }

    public long addKhoaHoc(KhoaHoc khoaHoc) {
        ContentValues v = new ContentValues();
        v.put("ten", khoaHoc.getTen());
        v.put("ngay", khoaHoc.getNgay());
        v.put("chuyennganh", khoaHoc.getChuyenNganh());
        v.put("kichhoat",khoaHoc.getKichhoat());
        SQLiteDatabase st = getWritableDatabase();
        return st.insert("Khoahoc", null, v);
    }

    public List<KhoaHoc> getAllKhoaHoc() {
        List<KhoaHoc> listKhoaHoc = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor cursor = statement.query("khoahoc", null,
                null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            KhoaHoc khoaHoc = new KhoaHoc(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)
            );
            listKhoaHoc.add(khoaHoc);
        }
        return listKhoaHoc;
    }

    public KhoaHoc getKhoaHocById(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.query("khoahoc", null, whereClause, whereArgs, null, null, null);

        if (cursor!=null && cursor.moveToNext()) {
            new KhoaHoc(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)
            );
        }
        return null;
    }



    public int updateKhoaHoc(KhoaHoc khoaHoc) {
        ContentValues v = new ContentValues();
        v.put("ten", khoaHoc.getTen());
        v.put("ngay", khoaHoc.getNgay());
        v.put("chuyennganh", khoaHoc.getChuyenNganh());
        v.put("kichhoat",khoaHoc.getKichhoat());
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(khoaHoc.getId())};
        SQLiteDatabase st = getWritableDatabase();
        return st.update("khoahoc", v, whereClause, whereArgs);
    }

    public int deleteKhoaHoc(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("khoahoc", whereClause, whereArgs);
    }

    public List<KhoaHoc> getKhoaHocByName(String name){
        List<KhoaHoc> listKhoaHoc=new ArrayList<>();
        String whereClause="ten like ?";
        String [] whereArgs={"%"+name+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor cursor=st.query("khoahoc",null,whereClause,whereArgs,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            KhoaHoc khoaHoc = new KhoaHoc(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)
            );
            listKhoaHoc.add(khoaHoc);
        }
    return listKhoaHoc;
    }
    public List<KhoaHoc> getKhoaHocByKH(int kh){
        List<KhoaHoc> listKhoaHoc=new ArrayList<>();
        String whereClause="kichhoat = ?";
        String [] whereArgs={String.valueOf(kh)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor cursor=st.query("khoahoc",null,whereClause,whereArgs,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            KhoaHoc khoaHoc = new KhoaHoc(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4)
            );
            listKhoaHoc.add(khoaHoc);
        }
        return listKhoaHoc;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
