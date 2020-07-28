package com.GureevInc.sqlliteandroidapplication.DAO;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.GureevInc.sqlliteandroidapplication.entities.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Query("select * from books")
    LiveData<List<Book>> getAllBooksInList();

    @Query("select * from books where id = :id")
    Book getBookById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllBooks (Book ... books);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBook(Book ... books);

    @Delete
    void deleteBook(Book book);

    @Query("delete from books")
    void clear();
 }
