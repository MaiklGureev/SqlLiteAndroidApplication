package com.GureevInc.sqlliteandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.GureevInc.sqlliteandroidapplication.DAO.BookDAO;
import com.GureevInc.sqlliteandroidapplication.DAO.GenreDAO;
import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AlertDialogActions alertDialogActions;
    private MyViewModel myViewModel;
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialogActions = new AlertDialogActions();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        bookListAdapter = new BookListAdapter(this);
        recyclerView.setAdapter(bookListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.getListLiveDataBookItems().observe(this, new Observer<List<BookItem>>() {
            @Override
            public void onChanged(List<BookItem> bookItems) {
                bookListAdapter.setBooks(bookItems);
            }
        });

        myViewModel.getListLiveDataGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                bookListAdapter.setGenreList(genres);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buttonAddBook: {
                alertDialogActions.showAddNewBookAlertDialog(this,myViewModel.getListLiveDataGenres().getValue());
                break;
            }
            case R.id.buttonAddGenre: {
                alertDialogActions.showAddNewGenreAlertDialog(this);
                break;
            }
            case R.id.buttonClearDB: {
                myViewModel.clearDatabase();
                break;
            }
            case R.id.buttonOrderBy: {
                alertDialogActions.showOrderByAlertDialog(this,myViewModel,bookListAdapter);
                break;
            }
            case R.id.buttonDeleteGenres: {
                if(!myViewModel.getListLiveDataGenres().getValue().isEmpty()){
                    alertDialogActions.showDeleteGenreAlertDialog(this,myViewModel.getListLiveDataGenres().getValue());
                }
                else {
                    Toast.makeText(this,"No genres! ",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
