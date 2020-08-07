package com.smart.myapplication2020customview.utils;

import android.database.Cursor;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class DAO {

    public static void saveAvatarTemplate(String avatarPath) {
        String sql = "select ObjectGUID from  User where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
        Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
        if (cursor == null || !cursor.moveToFirst()) {
            saveAvatar(avatarPath);
        } else {
            updateAvatar(avatarPath);
        }
    }

    private static void saveAvatar(String avatarPath) {
        try {
            String sql = "insert into User (ObjectGUID, Avatar, Nickname, CreateTime) values ('"
                    + Constants.TABLE_USER_INFO_OBJECT_GUID
                    + "','"
                    + avatarPath
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateAvatar(String avatarPath) {
        try {
            String sql = "update User set Avatar = '" + avatarPath + "' where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAvatar() {
        try {
            String result;
            String sql = "select Avatar from User where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            result = cursor.getString(0);
            cursor.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveNicknameTemplate(String nickname) {
        try {
            String sql = "select ObjectGUID from  User where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null || !cursor.moveToFirst()) {
                saveNickname(nickname);
            } else {
                updateNickname(nickname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveNickname(String nickname) {
        try {
            String sql = "insert into User (ObjectGUID, Avatar, Nickname, CreateTime) values ('"
                    + Constants.TABLE_USER_INFO_OBJECT_GUID
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + nickname
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateNickname(String nickname) {
        try {
            String sql = "update User set Nickname = '" + nickname + "' where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickname() {
        try {
            String result;
            String sql = "select Nickname from User where ObjectGUID = '" + Constants.TABLE_USER_INFO_OBJECT_GUID + "'";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            result = cursor.getString(0);
            cursor.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String saveTrajectoryTemplate(String ...trajectoryInfo){
//        try {
//            if(trajectoryInfo != null && trajectoryInfo.length >0){
//                if(!TextUtils.isEmpty(trajectoryInfo[0])){
//                    String sql = "select ObjectGUID from  Location where ObjectGUID = '" + trajectoryInfo[0] + "'";
//                    Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
//                    if (cursor == null || !cursor.moveToFirst()) {
//                        return saveTrajectory(trajectoryInfo);
//                    } else {
//                        return updateTrajectory(trajectoryInfo);
//                    }
//                }else{
//                    return saveTrajectory(trajectoryInfo);
//                }
//            }else{
//                return saveTrajectory(trajectoryInfo);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String saveTrajectory(String ...trajectoryInfo){
//        String objectGUID = null;
//        String sql = null;
//        if(trajectoryInfo != null && trajectoryInfo.length > 0){
//            if(!TextUtils.isEmpty(trajectoryInfo[0])){
//                objectGUID = trajectoryInfo[0];
//                if(trajectoryInfo.length == 3){
//                    sql = "insert into Location (ObjectGUID, ParentGUID, ModelName, RunTime, Distance, LatLon, CreateTime) values ('"
//                            + objectGUID
//                            + "','"
//                            + Constants.PLACE_HOLDER_NULL
//                            + "','"
//                            + Constants.LOCATION_MODEL_TRAJECTORY
//                            + "','"
//                            + trajectoryInfo[1]
//                            + "','"
//                            + trajectoryInfo[2]
//                            + "','"
//                            + Constants.PLACE_HOLDER_NULL
//                            + "','"
//                            + DateUtils.toDateString(new Date())
//                            + "')";
//                }
//            }else{
//                objectGUID = UUID.randomUUID().toString();
//                sql = "insert into Location (ObjectGUID, ParentGUID, ModelName, RunTime, Distance, LatLon, CreateTime) values ('"
//                        + objectGUID
//                        + "','"
//                        + Constants.PLACE_HOLDER_NULL
//                        + "','"
//                        + Constants.LOCATION_MODEL_TRAJECTORY
//                        + "','"
//                        + Constants.PLACE_HOLDER_NULL
//                        + "','"
//                        + Constants.PLACE_HOLDER_NULL
//                        + "','"
//                        + Constants.PLACE_HOLDER_NULL
//                        + "','"
//                        + DateUtils.toDateString(new Date())
//                        + "')";
//            }
//        }
//        DatabaseUtils.getSQLiteDatabase().execSQL(sql);
//        return objectGUID;
//    }
//
//    public static String updateTrajectory(String ...trajectoryInfo){
//
//        return null;
//    }

    public static String saveTrajectory(){
        try {
            String objectGUID = UUID.randomUUID().toString();
            String sql = "insert into Location (ObjectGUID, ParentGUID, ModelName, Thumbnail, RunTime, Distance, LatLon, CreateTime) values ('"
                    + objectGUID
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.LOCATION_MODEL_TRAJECTORY
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
            return objectGUID;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getTrajectory(String trajectoryId){
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "select Thumbnail, RunTime, Distance, CreateTime from Location where ObjectGUID = '" + trajectoryId + "'";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            sb.append(cursor.getString(0)).append(Constants.PLACE_HOLDER_COMMA);
            sb.append(cursor.getString(1)).append(Constants.PLACE_HOLDER_COMMA);
            sb.append(cursor.getString(2)).append(Constants.PLACE_HOLDER_COMMA);
            sb.append(cursor.getString(3));
            cursor.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getTrajectories() {
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = "select ObjectGUID, Thumbnail, RunTime, Distance, CreateTime from Location where ModelName = '" + Constants.LOCATION_MODEL_TRAJECTORY + "'  order by CreateTime desc";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null)
                return list;
            if (cursor.getCount() == 0) {
                cursor.close();
                return list;
            }
            StringBuilder sb;
            while (cursor.moveToNext()) {
                sb = new StringBuilder();
                sb.append(cursor.getString(0)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(1)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(2)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(3)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(4));
                list.add(sb.toString());
            }
            cursor.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void updateTrajectoryRunTime(String trajectoryId, String runTime){
        try {
            String sql = "update Location set RunTime = '" + runTime + "' where ObjectGUID = '" + trajectoryId + "'";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateTrajectoryDistance(String trajectoryId, String distance){
        try {
            String sql = "update Location set Distance = '" + distance + "' where ObjectGUID = '" + trajectoryId + "'";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateTrajectoryThumbnail(String trajectoryId, String thumbnail){
        try {
            String sql = "update Location set Thumbnail = '" + thumbnail + "' where ObjectGUID = '" + trajectoryId + "'";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteTrajectory(String trajectoryId){
        String sql = "delete from Location where  ObjectGUID = '" + trajectoryId + "'";
        DatabaseUtils.getSQLiteDatabase().execSQL(sql);
    }

    public static void savePoint(@NonNull String trajectoryId, @NonNull String latLon){
        try {
            String sql = "insert into Location (ObjectGUID, ParentGUID, ModelName, RunTime, Distance, LatLon, CreateTime) values ('"
                    + UUID.randomUUID().toString()
                    + "','"
                    + trajectoryId
                    + "','"
                    + Constants.LOCATION_MODEL_POINT
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + latLon
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getPoints(@NonNull String trajectoryId){
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = "select LatLon from Location where ParentGUID = '" + trajectoryId + "' and ModelName = '" + Constants.LOCATION_MODEL_POINT + "' order by CreateTime asc";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null)
                return list;
            if (cursor.getCount() == 0) {
                cursor.close();
                return list;
            }
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }
            cursor.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void saveCompressedPoint(@NonNull String trajectoryId, @NonNull  String latLon){
        try {
            String sql = "insert into Location (ObjectGUID, ParentGUID, ModelName, RunTime, Distance, LatLon, CreateTime) values ('"
                    + UUID.randomUUID().toString()
                    + "','"
                    + trajectoryId
                    + "','"
                    + Constants.LOCATION_MODEL_COMPRESSED_POINT
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + latLon
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getCompressedPoints(@NonNull String trajectoryId){
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = "select LatLon from Location where ParentGUID = '" + trajectoryId + "' and ModelName = '" + Constants.LOCATION_MODEL_COMPRESSED_POINT + "' order by CreateTime asc";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null)
                return list;
            if (cursor.getCount() == 0) {
                cursor.close();
                return list;
            }
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0));
            }
            cursor.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void saveIdea(@NonNull String trajectoryId, @NonNull  String latLon, @NonNull String idea){
        try {
            String sql = "insert into Idea (ObjectGUID, ParentGUID, LatLon, Idea, Image, CreateTime) values ('"
                    + UUID.randomUUID().toString()
                    + "','"
                    + trajectoryId
                    + "','"
                    + latLon
                    + "','"
                    + idea
                    + "','"
                    + Constants.PLACE_HOLDER_NULL
                    + "','"
                    + DateUtils.toDateString(new Date())
                    + "')";
            DatabaseUtils.getSQLiteDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getIdeas(@NonNull String trajectoryId){
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = "select LatLon, Idea, CreateTime from Idea where ParentGUID = '" + trajectoryId + "'  order by CreateTime asc";
            Cursor cursor = DatabaseUtils.getSQLiteDatabase().rawQuery(sql, null);
            if (cursor == null)
                return list;
            if (cursor.getCount() == 0) {
                cursor.close();
                return list;
            }
            StringBuilder sb;
            while (cursor.moveToNext()) {
                sb = new StringBuilder();
                sb.append(cursor.getString(0)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(1)).append(Constants.PLACE_HOLDER_COMMA);
                sb.append(cursor.getString(2));
                list.add(sb.toString());
            }
            cursor.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}





























































