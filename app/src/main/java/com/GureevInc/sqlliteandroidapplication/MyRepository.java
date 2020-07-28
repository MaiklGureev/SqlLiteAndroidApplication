package com.GureevInc.sqlliteandroidapplication;

import android.app.Application;
import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.GureevInc.sqlliteandroidapplication.DAO.BookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.CustomBookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.GenreDAO;
import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.List;

public class MyRepository {

    private BookDAO bookDAO;
    private GenreDAO genreDAO;
    private CustomBookDAO customBookDAO;

    private LiveData<List<Book>> bookList;
    private LiveData<List<Genre>> genreList;
    private LiveData<Cursor> customBooks;

    public MyRepository(Application application) {
        MyDB myDB = MyDB.getInstance(application.getApplicationContext());
        bookDAO = myDB.bookDAO();
        genreDAO = myDB.genreDAO();
        bookList = bookDAO.getAllBooksInList();
        genreList = genreDAO.getAllGenres();
        customBooks = customBookDAO.getCustomBooksInCursor();
    }



    public LiveData<List<Book>> getBookList() {
        return bookList;
    }

    public LiveData<List<Genre>> getGenreList() {
        return genreList;
    }

    public LiveData<Cursor> getCustomBooks() {
        return customBooks;
    }

    public void insertBook(final Book book) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDAO.insertAllBooks(book);
            }
        });
    }

    public void insertGenre(final Genre genre) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { genreDAO.insertAllGenres(genre);
            }
        });
    }

}