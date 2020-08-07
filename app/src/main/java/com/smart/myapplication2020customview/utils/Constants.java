package com.smart.myapplication2020customview.utils;

import android.os.Environment;

import com.smart.myapplication2020customview.framework.BaseApplication;

import java.io.File;

public interface Constants {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          调试相关                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    boolean DEBUG = true;
    String DEBUG_KEY = "DEBUG_KEY";
    boolean DEBUG_SWITCH_DEFAULT_VALUE = false;
    boolean DEBUG_SHOW_TOAST = true;
    String TAG = "TAG";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          通用相关                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    String PLACE_HOLDER_NULL = "";
    String PLACE_HOLDER_BLANK = " ";
    String PLACE_HOLDER_UNAVAILABLE = "--";
    String PLACE_HOLDER_COMMA = ",";
    String PLACE_HOLDER_TRAJECTORY_CREATE_TIME = "00:00:00";
    String PLACE_HOLDER_TRAJECTORY_DISTANCE = "0.000 米";
    String MONEY_SYMBOL_RMB = "¥";
    String MONEY_SYMBOL_DOLLAR = "$";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          状态栏                                             //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //状态栏相关
    String STATUS_BAR_HEIGHT = "status_bar_height";
    String DIMEN = "dimen";
    String ANDROID = "android";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          登录相关                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    String LOGIN_USER_NAME = "admin";
    String LOGIN_PASSWORD = "123";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          数字常量                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //权限申请
    int PERMISSION_REQUEST_CODE = 0x001024;
    //头像更改
    int AVATAR_TAKE_PHOTO = 0x001025;
    int AVATAR_SELECT_PIC = 0x001026;
    int AVATAR_CROP_PHOTO = 0x001027;
    int INTENT_REQUEST_CODE_MODIFY_NICKNAME = 0x001028;
    int INTENT_REQUEST_CODE_WRITE_IDEA = 0x001029;
    int DELAY_SHORT = 800;
    int DELAY_NORMAL = 1200;
    int DELAY_LONG = 1800;
    int DELAY_LONG_X = 3000;
    int DELAY_LONG_XX = 5000;
    //扩大点击区域
    int EXPEND_VIEW_FOUR_EIGHT = 48;
    int NUMBER_ZERO = 0;
    int NUMBER_ONE = 1;
    int NUMBER_TWO = 2;
    int NUMBER_THREE = 3;
    int NUMBER_FOUR = 4;
    int NUMBER_FIVE = 5;
    int NUMBER_SIX = 6;
    int NUMBER_SEVEN = 7;
    int NUMBER_EIGHT = 8;
    int NUMBER_NINE = 9;
    int NUMBER_TEN = 10;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          INTENT                                            //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    String INTENT_RESULT_KEY_BUNDLE = "INTENT_RESULT_KEY_BUNDLE";
    String INTENT_RESULT_KEY_MODIFY_NICKNAME = "INTENT_RESULT_KEY_MODIFY_NICKNAME";
    String INTENT_PARAM_KEY_WRITE_IDEA_TRAJECTORY_ID = "INTENT_PARAM_KEY_WRITE_IDEA_TRAJECTORY_ID";
    String INTENT_PARAM_KEY_WRITE_IDEA_LATLON = "INTENT_PARAM_KEY_WRITE_IDEA_LATLON";
    String INTENT_PARAM_KEY_HISTORY_DETAIL = "INTENT_PARAM_KEY_HISTORY_DETAIL";
    String INTENT_PARAM_KEY_HISTORY_IDEA_DETAIL = "INTENT_PARAM_KEY_HISTORY_IDEA_DETAIL";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          文件相关                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //数据库相关
    String FILE_DATABASE_DIRECTORY = "database";
    String FILE_DATABASE = "database.db";
    String CREATE_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS Location (ObjectGUID PRIMARY KEY, ParentGUID, ModelName, Thumbnail, RunTime, Distance, LatLon, CreateTime)";
    String CREATE_IDEA_TABLE = "CREATE TABLE IF NOT EXISTS Idea (ObjectGUID PRIMARY KEY, ParentGUID, LatLon, Idea, Image, CreateTime)";
    String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User (ObjectGUID PRIMARY KEY, Avatar, Nickname, CreateTime)";
    String TABLE_LOCATION = "Location";
    String TABLE_IDEA = "Idea";
    String TABLE_USER = "User";
    String TABLE_USER_INFO_OBJECT_GUID = "0x2018100418371002";
    //照片
    String FILE_IMAGE_DIRECTORY = "image";
    String FILE_IMAGE_DIRECTORY_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BaseApplication.getInstance().getPackageName()
            + File.separator + Constants.FILE_IMAGE_DIRECTORY;
    String FILE_IMAGE_AVATAR_DIRECTORY = "avatar";
    String FILE_IMAGE_AVATAR_DIRECTORY_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BaseApplication.getInstance().getPackageName()
            + File.separator + Constants.FILE_IMAGE_DIRECTORY
            + File.separator + Constants.FILE_IMAGE_AVATAR_DIRECTORY;
    String FILE_IMAGE_TRAJECTORY_DIRECTORY = "trajectory";
    String FILE_IMAGE_TRAJECTORY_DIRECTORY_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BaseApplication.getInstance().getPackageName()
            + File.separator + Constants.FILE_IMAGE_DIRECTORY
            + File.separator + Constants.FILE_IMAGE_TRAJECTORY_DIRECTORY;
    String FILE_IMAGE_TRAJECTORY_THUMBNAIL_NAME = "TRAJECTORY";
    String FILE_IMAGE_TRAJECTORY_THUMBNAIL_FILE_NAME = FILE_IMAGE_TRAJECTORY_THUMBNAIL_NAME + Constants.FILE_IMAGE_SUFFIX;
    //头像文件名称
    String FILE_IMAGE_AVATAR_NAME = "AVATAR";
    String FILE_IMAGE_AVATAR_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BaseApplication.getInstance().getPackageName()
            + File.separator + Constants.FILE_IMAGE_DIRECTORY
            + File.separator + Constants.FILE_IMAGE_AVATAR_DIRECTORY
            + File.separator + Constants.FILE_IMAGE_AVATAR_NAME + Constants.FILE_IMAGE_SUFFIX;
    String FILE_IMAGE_AVATAR_TEMP_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BaseApplication.getInstance().getPackageName()
            + File.separator + Constants.FILE_IMAGE_DIRECTORY
            + File.separator + Constants.FILE_IMAGE_AVATAR_DIRECTORY
            + File.separator + System.currentTimeMillis() + Constants.FILE_IMAGE_SUFFIX;
    String FILE_IMAGE_SUFFIX = ".JPG";
    //图库
    String GALLERY_IMAGE_TYPE = "image/*";
    //图片裁剪
    String INTENT_ACTION_CROP = "com.android.camera.action.CROP";
    String INTENT_EXTRA_SCALE = "scale";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          位置相关                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    String LOCATION_NETWORK = "NETWORK_LOCATION.SMART.COM";
    String LOCATION_GPS = "GPS_LOCATION.SMART.COM";
    String LOCATION_GAO_DE = "GAO_DE_LOCATION.SMART.COM";
    String LOCATION_TRAJECTORY_ID = "LOCATION_TRAJECTORY_ID.SMART.COM";
    String LOCATION_TRAJECTORY_DISTANCE = "LOCATION_TRAJECTORY_DISTANCE.SMART.COM";
    String LOCATION_TRAJECTORY_DURATION = "LOCATION_TRAJECTORY_DURATION.SMART.COM";
    String LOCATION_TRAJECTORY_POINTS = "LOCATION_TRAJECTORY_POINTS.SMART.COM";
    int LOCATION_TRAJECTORY_ONE_MILE = 1000;
    String LOCATION_MODEL_TRAJECTORY = "Trajectory";
    String LOCATION_MODEL_POINT = "Point";
    String LOCATION_MODEL_COMPRESSED_POINT = "CompressedPoint";
    String LOCATION_UNIT_M = "米";
    String LOCATION_TRAJECTORY_DISTANCE_INITIAL_VALUE = "0.000";
    String LOCATION_UNIT_KM = "公里";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                            //
    //                                          地图切换                                           //
    //                                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    String MAP_THEME_KEY = "MAP_THEME_KEY";
    int MAP_THEME_NORMAL = 0x001030;
    int MAP_THEME_SATELLITE = 0x001031;
    int MAP_THEME_BUS = 0x001032;

}
