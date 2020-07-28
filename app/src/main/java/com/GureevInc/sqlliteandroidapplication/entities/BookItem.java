package com.GureevInc.sqlliteandroidapplication.entities;

public class BookItem extends Book {
    public String genre;
    public BookItem(int genreId, String title, String author, int countPages) {
        super(genreId, title, author, countPages);
    }


}
