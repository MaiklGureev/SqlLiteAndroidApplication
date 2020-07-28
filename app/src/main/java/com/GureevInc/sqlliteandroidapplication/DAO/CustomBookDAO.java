package com.GureevInc.sqlliteandroidapplication.DAO;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CustomBookDAO {

        @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
                "from books " +
                "join genres on books.genreId = genres.id")
        LiveData<Cursor> getCustomBooksInCursor();
}
