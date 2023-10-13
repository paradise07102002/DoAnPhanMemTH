package edu.huflit.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.huflit.myapplication.OOP.LoaiSach;
import edu.huflit.myapplication.OOP.User;

public class MyDatabase {
    SQLiteDatabase database;
    DBHelper helper;

    public MyDatabase(Context context)
    {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }
    public User checkRole(String username)
    {
        User user = new User();
        String select = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USERNAME_USER + " = " + "'" + username + "'";
        Cursor cursor = database.rawQuery(select, null);
        if (cursor != null)
        {
            int role_index = cursor.getColumnIndex(DBHelper.ROLE_USER);
            cursor.moveToFirst();
            user.setRole_user(cursor.getString(role_index));
        }
        cursor.close();
        return user;
    }
    public boolean checkLogin(String username, String password)
    {
        String select = "SELECT * FROM " + DBHelper.TABLE_USER + " WHERE " + DBHelper.USERNAME_USER + " = " + "'" + username + "'" + " AND " + DBHelper.PASSWORD_USER + " = " + "'" + password + "'";
        Cursor cursor = database.rawQuery(select, null);
        if (cursor.moveToFirst() == false)
        {
            return false;//Ko đúng user or pass
        }
        else
        {
            return true;
        }
    }
    public Cursor layDuLieuDauSach()
    {
        String[] cot = {DBHelper.MA_LOAI_SACH_LS, DBHelper.TEN_LOAI_SACH_LS};
        Cursor cursor = database.query(DBHelper.TABLE_LOAI_SACH, cot, null, null, null, null, null);
        return cursor;
    }
    public long xoaDauSach(int ma_dau_sach)
    {
        return database.delete(DBHelper.TABLE_LOAI_SACH, DBHelper.MA_LOAI_SACH_LS + " = " + "'" + ma_dau_sach + "'", null);
    }
    public Cursor layDuLieuDauSachByID(int ma_dau_sach)
    {
        String select = "SELECT * FROM " + DBHelper.TABLE_LOAI_SACH + " WHERE " + DBHelper.MA_LOAI_SACH_LS + " = " + ma_dau_sach;
        Cursor cursor = database.rawQuery(select, null);
        return cursor;
    }
    public long suaDauSach(LoaiSach loaiSach)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.MA_LOAI_SACH_LS, loaiSach.getMa_loai_sach_ls());
        contentValues.put(DBHelper.TEN_LOAI_SACH_LS, loaiSach.getLoai_sach_ls());
        return database.update(DBHelper.TABLE_LOAI_SACH, contentValues, DBHelper.MA_LOAI_SACH_LS + " = " + loaiSach.getMa_loai_sach_ls(), null);
    }
    public boolean kiemTraTenDS(String ten_dau_sach)
    {
        String select = "SELECT * FROM " + DBHelper.TABLE_LOAI_SACH + " WHERE " + DBHelper.TEN_LOAI_SACH_LS + " = " + "'" + ten_dau_sach + "'";
        Cursor cursor = database.rawQuery(select, null);
        if (cursor.moveToFirst() == true)
        {
            return true;//có tồn tại
        }
        else
        {
            return false;
        }
    }
    public boolean checkTenDauSach(String ten_dau_sach)
    {
        //Truy vấn lấy cột mã loại sách nếu mã loại sách nhập vào có trong Database
        String select = "SELECT " + DBHelper.MA_LOAI_SACH_LS  + " FROM " + DBHelper.TABLE_LOAI_SACH + " WHERE " + DBHelper.TEN_LOAI_SACH_LS + " = " + "'" + ten_dau_sach + "'";
        //Đưa kết quả truy vấn vào trong cursor/ Do mã sách là duy nhất nên chỉ có thể tìm ra tối đa 1 đối tượng mã sách
        Cursor cursor = database.rawQuery(select, null);
        if (cursor.moveToFirst())
        {
            return true;//Mã loại sách tồn tại
        }
        return false;//Mã loại sách không tồn tại
    }
    public long addLoaiSach(LoaiSach loaiSach)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TEN_LOAI_SACH_LS, loaiSach.getLoai_sach_ls());
        return database.insert(DBHelper.TABLE_LOAI_SACH, null, values);
    }
}
