package com.GureevInc.sqlliteandroidapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GureevInc.sqlliteandroidapplication.DAO.BookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.GenreDAO;
import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.List;

public class MyRepository {

    private BookDAO bookDAO;
    private GenreDAO genreDAO;


    private LiveData<List<Book>> bookList;
    private LiveData<List<Genre>> genreList;
    private LiveData<List<BookItem>> bookItemList;

    private LiveData<List<BookItem>> bookItemListOrderByGenre;
    private LiveData<List<BookItem>> bookItemListOrderByTitle;
    private LiveData<List<BookItem>> bookItemListOrderByAuthor;
    private LiveData<List<BookItem>> bookItemListOrderByCountPage;

    private static volatile MyRepository instance;

    private MyRepository(Application application) {
        MyDB myDB = MyDB.getInstance(application.getApplicationContext());

        bookDAO = myDB.bookDAO();
        genreDAO = myDB.genreDAO();

        bookList = bookDAO.getAllBooksInList();
        genreList = genreDAO.getAllGenres();
        bookItemList = bookDAO.getBookItems();

        bookItemListOrderByGenre = bookDAO.getBookItemsOrderByGenre();
        bookItemListOrderByTitle = bookDAO.getBookItemsOrderByTitle();
        bookItemListOrderByAuthor = bookDAO.getBookItemsOrderByAuthor();
        bookItemListOrderByCountPage = bookDAO.getBookItemsOrderByCountPages();
    }

    public static MyRepository getInstance(Application application){
        MyRepository singleton = instance;
        if(singleton!=null){
            return singleton;
        }
        synchronized (MyRepository.class){
            if(instance == null){
                instance = new MyRepository(application);
            }
            return instance;
        }
    }


    public LiveData<List<BookItem>> getBookItemList() {
        return bookItemList;
    }

    public LiveData<List<Book>> getBookList() {
        return bookList;
    }

    public LiveData<List<Genre>> getGenreList() {
        return genreList;
    }

    public LiveData<List<BookItem>> getBookItemListOrderByGenre() {
        return bookItemListOrderByGenre;
    }

    public LiveData<List<BookItem>> getBookItemListOrderByTitle() {
        return bookItemListOrderByTitle;
    }

    public LiveData<List<BookItem>> getBookItemListOrderByAuthor() {
        return bookItemListOrderByAuthor;
    }

    public LiveData<List<BookItem>> getBookItemListOrderByCountPage() {
        return bookItemListOrderByCountPage;
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
    public void updateBook(final Book book) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDAO.updateBook(book);
            }
        });
    }

    public void updateGenre(final Genre genre) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { genreDAO.updateGenres(genre);
            }
        });
    }

    public void deleteBookById(final int bookId) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDAO.deleteBookById(bookId);
            }
        });
    }

    public void deleteGenreById(final int genreId) {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { genreDAO.deleteGenresById(genreId);
            }
        });
    }


    public void clearDatabase() {
        MyDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                genreDAO.clear();
                bookDAO.clear();
            }
        });
    }
}