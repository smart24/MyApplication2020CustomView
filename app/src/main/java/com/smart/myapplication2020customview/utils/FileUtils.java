package com.smart.myapplication2020customview.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.smart.myapplication2020customview.utils.PermissionsUtils.isExternalStorageAvailable;


public class FileUtils {

    public static File createFileDirectory(String filePath) {
        if (isExternalStorageAvailable() && !TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            return file;
        }
        return null;
    }

    public static File createFile(String filePath) {
        if (isExternalStorageAvailable() && !TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                    return file;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return file;
            }
        }
        return null;
    }

    public static void deleteFile(String filePath) {
        if (isExternalStorageAvailable() && !TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void deleteFile(File file) {
        if (isExternalStorageAvailable() && file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void clearDir(File dir) {
        if (dir.isFile())
            return;

        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile())
                file.delete();
        }
    }

    public static File saveBitmap(String filePath, Bitmap bmp) {
        File file = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
