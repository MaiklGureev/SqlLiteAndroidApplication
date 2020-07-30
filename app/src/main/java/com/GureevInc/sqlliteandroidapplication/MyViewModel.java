package com.GureevInc.sqlliteandroidapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MyRepository myRepository;
    private LiveData<List<Book>> listLiveDataBooks;
    private LiveData<List<Genre>> listLiveDataGenres;
    private LiveData<List<BookItem>> listLiveDataBookItems;
    private LiveData<List<BookItem>> listLiveDataBookItemsOrderByGenre;
    private LiveData<List<BookItem>> listLiveDataBookItemsOrderByTitle;
    private LiveData<List<BookItem>> listLiveDataBookItemsOrderByAuthor;
    private LiveData<List<BookItem>> listLiveDataBookItemsOrderByCountPage;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = MyRepository.getInstance(application);
        listLiveDataBooks = myRepository.getBookList();
        listLiveDataGenres = myRepository.getGenreList();
        listLiveDataBookItems = myRepository.getBookItemList();
        listLiveDataBookItemsOrderByGenre = myRepository.getBookItemListOrderByGenre();
        listLiveDataBookItemsOrderByTitle = myRepository.getBookItemListOrderByTitle();
        listLiveDataBookItemsOrderByAuthor = myRepository.getBookItemListOrderByAuthor();
        listLiveDataBookItemsOrderByCountPage = myRepository.getBookItemListOrderByCountPage();


    }

    public LiveData<List<BookItem>> getListLiveDataBookItemsOrderByGenre() {
        return listLiveDataBookItemsOrderByGenre;
    }

    public LiveData<List<BookItem>> getListLiveDataBookItemsOrderByTitle() {
        return listLiveDataBookItemsOrderByTitle;
    }

    public LiveData<List<BookItem>> getListLiveDataBookItemsOrderByAuthor() {
        return listLiveDataBookItemsOrderByAuthor;
    }

    public LiveData<List<BookItem>> getListLiveDataBookItemsOrderByCountPage() {
        return listLiveDataBookItemsOrderByCountPage;
    }

    public LiveData<List<BookItem>> getListLiveDataBookItems() {
        return listLiveDataBookItems;
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

    public void deleteBookById(int bookId) {
        myRepository.deleteBookById(bookId);
    }

    public void clearDatabase() {
        myRepository.clearDatabase();
    }



}
