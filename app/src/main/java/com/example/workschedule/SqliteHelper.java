package com.example.workschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {


    public SqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //用于首次创建数据库中的room表，只在第一次创建
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_room_table = "create table room_info" +
                "(place varchar,begin_hour integer,begin_min integer,end_hour integer,end_min integer,persons varchar)";
        sqLiteDatabase.execSQL(create_room_table);
        sqLiteDatabase.close();
    }

    //数据库版本升级（用于添加列）
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
