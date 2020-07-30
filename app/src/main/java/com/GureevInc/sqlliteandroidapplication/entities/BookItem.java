package com.GureevInc.sqlliteandroidapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity
public class BookItem  {


    public int id;
    public int genreId;
    public String title;
    public String author;
    public int countPages;

    @ColumnInfo(name = "name")
    public String genre;

    public BookItem(int id, int genreId, String title, String author, int countPages, String genre) {
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.author = author;
        this.countPages = countPages;
        this.genre = genre;
    }
}
