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
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;

import java.util.List;

@Dao
public interface BookDAO {

    @Query("select * from books")
    LiveData<List<Book>> getAllBooksInList();

    @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
            "from books " +
            "join genres on books.genreId = genres.id")
    LiveData<List<BookItem>> getBookItems();

    @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
            "from books " +
            "join genres on books.genreId = genres.id " +
            "order by genres.name ")
    LiveData<List<BookItem>> getBookItemsOrderByGenre();

    @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
            "from books " +
            "join genres on books.genreId = genres.id " +
            "order by title")
    LiveData<List<BookItem>> getBookItemsOrderByTitle();

    @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
            "from books " +
            "join genres on books.genreId = genres.id " +
            "order by author")
    LiveData<List<BookItem>> getBookItemsOrderByAuthor();

    @Query("select  books.id,books.genreId,genres.name,books.title,books.author,books.countPages " +
            "from books " +
            "join genres on books.genreId = genres.id " +
            "order by countPages")
    LiveData<List<BookItem>> getBookItemsOrderByCountPages();

    @Query("select * from books where id = :id")
    Book getBookById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllBooks (Book ... books);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBook(Book ... books);

    @Delete
    void deleteBook(Book book);

    @Query("delete from books where books.id = :id")
    void deleteBookById(int id);

    @Query("delete from books")
    void clear();
 }
