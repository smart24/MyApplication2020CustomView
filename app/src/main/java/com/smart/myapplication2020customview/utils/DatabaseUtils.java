package com.smart.myapplication2020customview.utils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;

import com.smart.myapplication2020customview.framework.BaseApplication;

import java.io.File;

public class DatabaseUtils {

    private static SQLiteDatabase database;

    public static SQLiteDatabase getSQLiteDatabase() {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + BaseApplication.getInstance().getPackageName()
                + File.separator + Constants.FILE_DATABASE_DIRECTORY
                + File.separator + Constants.FILE_DATABASE;
        File dbFile = FileUtils.createFileDirectory(filePath);
        return getSQLiteDatabase(dbFile);
    }

    public static SQLiteDatabase getSQLiteDatabase(File dbFile) {
        if (database == null) {
            database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        }
        return database;
    }

    public static void createTable(String sql) {
        if (!TextUtils.isEmpty(sql)) {
            getSQLiteDatabase().execSQL(sql);
        }
    }
}
