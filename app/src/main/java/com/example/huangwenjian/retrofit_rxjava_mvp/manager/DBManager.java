package com.example.huangwenjian.retrofit_rxjava_mvp.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.huangwenjian.retrofit_rxjava_mvp.gen.DaoMaster;
import com.example.huangwenjian.retrofit_rxjava_mvp.gen.DaoOpenHelper;
import com.example.huangwenjian.retrofit_rxjava_mvp.gen.DaoSession;
import com.example.huangwenjian.retrofit_rxjava_mvp.singleton.SingleTonHolder;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.UIUtils;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 数据库管理器,用greendao实现
 * <p/>
 * 时间: 16/8/31
 */
public class DBManager {
    private static DBManager mInstance;
    public static final Context mContext = UIUtils.getContext();
    public static final String DEFAULT_DB_NAME = "test.db";
    public static final String MY_DB_NAME = "my.db";

    private DaoOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public DBManager(String dbName) {
        if (!TextUtils.isEmpty(dbName)) {
            setDatabase(dbName);
        }
    }

    /**
     * 设置数据库
     *
     * @param dbName 数据库名
     */
    private void setDatabase(String dbName) {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 DevOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoOpenHelper(mContext, dbName);
        SQLiteDatabase db = mHelper.getWritableDatabase();      //创建数据库的操作,包括建表的操作
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();                  //建立数据库连接对象
    }

    /**
     * 得到一个数据库实例
     *
     * @param dbName 数据库名,此数据库名应当事先以常量形式先定义好
     * @return
     */
    public static DBManager getInstance(String dbName) {
        if (!TextUtils.isEmpty(dbName)) {   //如果数据库名不为空,就用自定义的数据库名,并创建DBManager的实例
            if (TextUtils.equals(dbName, DBManager.MY_DB_NAME)) {
                return SingleTonHolder.MY_INSTANCE;
            }
        }
        return SingleTonHolder.DEFAULT_INSTANCE;
    }

    /**
     * 返回数据库连接对象
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
