package com.GureevInc.sqlliteandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.GureevInc.sqlliteandroidapplication.DAO.BookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.GenreDAO;
import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRepository  repository = new MyRepository(getApplication());

//        MyDB.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                genreDAO.insertAllGenres(new Genre("роман"), new Genre("фантастика"), new Genre("рассказ"));
//
//                bookDAO.insertAllBooks(new Book(16,"abc","aaa",100),
//                        new Book(17,"cdv","aaa",100),
//                        new Book(17,"qwerty","vvv",200),
//                        new Book(17,"zxcv","ccc",150),
//                        new Book(17,"vcz","bbb",50)
//                );
//
//                List<Book> bookList = bookDAO.getAllBooksInList();
//                List<Genre> genreList = genreDAO.getAllGenres();
//                Log.d(TAG, "onCreate: " + genreList.toString());
//                Log.d(TAG, "onCreate: " + bookList.toString());
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        BookListAdapter bookListAdapter = new BookListAdapter(this);
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }
}
