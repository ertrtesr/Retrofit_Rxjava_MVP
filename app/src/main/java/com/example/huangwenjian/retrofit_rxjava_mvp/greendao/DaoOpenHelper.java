package com.example.huangwenjian.retrofit_rxjava_mvp.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.huangwenjian.retrofit_rxjava_mvp.utils.LogUtils;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/31
 */
public class DaoOpenHelper extends DaoMaster.OpenHelper {

    public DaoOpenHelper(Context context, String name) {
        this(context, name, null);
    }

    public DaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.i("数据库版本从version: " + oldVersion + "更新为: " + newVersion);

        db.execSQL("ALTER TABLE USER ADD PHONE TEXT");
    }
}
