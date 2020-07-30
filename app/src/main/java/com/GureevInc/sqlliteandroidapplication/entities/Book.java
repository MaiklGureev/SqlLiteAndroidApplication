package com.GureevInc.sqlliteandroidapplication.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ForeignKey(entity = Genre.class, parentColumns = "id", childColumns = "genreId")
    public int genreId;
    public String title;
    public String author;
    public int countPages;

    @Ignore
    public Book() {
    }

    @Ignore
    public Book(int id, int genreId, String title, String author, int countPages) {
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.author = author;
        this.countPages = countPages;
    }

    public Book(int genreId, String title, String author, int countPages) {
        this.genreId = genreId;
        this.title = title;
        this.author = author;
        this.countPages = countPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", genreId=" + genreId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", countPages=" + countPages +
                '}';
    }
}
