package com.GureevInc.sqlliteandroidapplication;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class RoomSingleton {

    private static volatile RoomSingleton instance;
    private RoomDatabase roomDatabase;

    private RoomSingleton(Context context, String dbName) {
        roomDatabase = Room.databaseBuilder(context, MyDB.class, dbName).build();
    }

    public static RoomSingleton getInstance(Context context) {
        RoomSingleton singleton = instance;
        if (instance != null) {
            return singleton;
        }
        synchronized (RoomSingleton.class) {
            if (instance==null){
                instance = new RoomSingleton(context,"myDB");
            }
            return instance;
        }
    }
}
