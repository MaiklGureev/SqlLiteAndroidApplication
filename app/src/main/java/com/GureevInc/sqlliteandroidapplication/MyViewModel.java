package com.GureevInc.sqlliteandroidapplication;

import android.app.Application;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    private LiveData<List<Book>> listLiveDataBooks;
    private LiveData<List<Genre>> listLiveDataGenres;
    private LiveData<Cursor> customBooks;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        listLiveDataBooks = myRepository.getBookList();
        listLiveDataGenres = myRepository.getGenreList();
        customBooks = myRepository.getCustomBooks();
    }

    public LiveData<List<Book>> getListLiveDataBooks() {
        return listLiveDataBooks;
    }

    public LiveData<List<Genre>> getListLiveDataGenres() {
        return listLiveDataGenres;
    }

    public void insertBook(Book book) {
        myRepository.insertBook(book);
    }

    public void insertGenre(Genre genre) {
        myRepository.insertGenre(genre);
    }

}
