package com.GureevInc.sqlliteandroidapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GureevInc.sqlliteandroidapplication.DAO.BookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.GenreDAO;
import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class, Genre.class}, version = 1,exportSchema = false)
public abstract class MyDB extends RoomDatabase {

    public abstract BookDAO bookDAO();
    public abstract GenreDAO genreDAO();

    private static volatile MyDB instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static MyDB getInstance(Context context) {
        MyDB singleton = instance;
        if (instance != null) {
            return singleton;
        }
        synchronized (MyDB.class) {
            if (instance==null){
                instance = Room.databaseBuilder(context.getApplicationContext(), MyDB.class, "MyDB")
                        .addCallback(callback)
                        .build();
            }
            return instance;
        }
    }

    private static MyDB.Callback callback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    };

}
