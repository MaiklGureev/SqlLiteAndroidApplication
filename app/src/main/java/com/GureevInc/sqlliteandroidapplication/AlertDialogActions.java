package com.GureevInc.sqlliteandroidapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.ArrayList;
import java.util.List;

public class AlertDialogActions {


    public void showMainAlertDialogForItem(final Activity context, final BookItem bookItem, final List<Genre> genres) {

        String[] actions = new String[]{"Edit", "Delete"};
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "Click on item "+which, Toast.LENGTH_SHORT).show();
                switch (which) {
                    case 0: {
                        showEditBookAlertDialog(context, bookItem, genres);
                        break;
                    }
                    case 1: {
                        showDeleteBookAlertDialog(context, bookItem.id);
                        break;
                    }
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Actions")
                .setItems(actions, onClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showDeleteBookAlertDialog(final Activity context, final int itemId) {

        DialogInterface.OnClickListener positiveAnswer = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyRepository.getInstance(context.getApplication()).deleteBookById(itemId);
                Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Do you want delete this item?")
                .setPositiveButton("Yes", positiveAnswer)
                .setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showEditBookAlertDialog(final Activity context, final BookItem bookItem, final List<Genre> genres) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_add_and_edit_book, null);

        final EditText title = view.findViewById(R.id.editTextTitle);
        final EditText author = view.findViewById(R.id.editTextAuthor);
        final EditText countPages = view.findViewById(R.id.editTextCountPages);
        final Spinner spinnerGenre = view.findViewById(R.id.spinnerGenre);

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, genres);
        spinnerGenre.setAdapter(spinnerAdapter);

        DialogInterface.OnClickListener positiveAnswer = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String titleNew = title.getText().toString();
                String authorNew = author.getText().toString();
                int countPagesNew = Integer.valueOf(countPages.getText().toString());
                int id = bookItem.id;
                int genreIdNew = ((Genre) (spinnerGenre.getSelectedItem())).id;
                Book updatedBook = new Book(id, genreIdNew, titleNew, authorNew, countPagesNew);
                MyRepository.getInstance(context.getApplication()).updateBook(updatedBook);
                Toast.makeText(context, "Current book updated!", Toast.LENGTH_SHORT).show();
            }
        };

        title.setText(bookItem.title);
        author.setText(bookItem.author);
        countPages.setText(String.valueOf(bookItem.countPages));
        spinnerGenre.setSelection(searchGenreIndex(genres, bookItem.genre));


        builder.setTitle("Edit book")
                .setView(view)
                .setPositiveButton("Ok", positiveAnswer)
                .setNegativeButton("Cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public void showAddNewBookAlertDialog(final Activity context, final List<Genre> genres) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_add_and_edit_book, null);

        final EditText title = view.findViewById(R.id.editTextTitle);
        final EditText author = view.findViewById(R.id.editTextAuthor);
        final EditText countPages = view.findViewById(R.id.editTextCountPages);
        final Spinner spinnerGenre = view.findViewById(R.id.spinnerGenre);

        final SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, genres);
        spinnerGenre.setAdapter(spinnerAdapter);
        spinnerGenre.setSelection(0);

        DialogInterface.OnClickListener positiveAnswer = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!spinnerAdapter.isEmpty()) {
                    String titleNew = title.getText().toString();
                    String authorNew = author.getText().toString();
                    int countPagesNew = Integer.valueOf(countPages.getText().toString());
                    int genreIdNew = ((Genre) (spinnerGenre.getSelectedItem())).id;
                    Book newBook = new Book(genreIdNew, titleNew, authorNew, countPagesNew);
                    MyRepository.getInstance(context.getApplication()).insertBook(newBook);
                    Toast.makeText(context, "New book inserted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Add genre firstly!", Toast.LENGTH_SHORT).show();
                }

            }
        };

        builder.setTitle("Add new book")
                .setView(view)
                .setPositiveButton("Ok", positiveAnswer)
                .setNegativeButton("Cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void showAddNewGenreAlertDialog(final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_add_and_edit_genre, null);

        final EditText genreNameEditText = view.findViewById(R.id.editTextGenre);


        DialogInterface.OnClickListener positiveAnswer = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nameGenre = genreNameEditText.getText().toString();
                Genre genre = new Genre(nameGenre);
                MyRepository.getInstance(context.getApplication()).insertGenre(genre);
                Toast.makeText(context, "New genre inserted!", Toast.LENGTH_SHORT).show();
            }
        };

        builder.setTitle("Add new genre")
                .setView(view)
                .setPositiveButton("Ok", positiveAnswer)
                .setNegativeButton("Cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showOrderByAlertDialog(final Activity context, final MyViewModel myViewModel, final BookListAdapter bookListAdapter) {
        final String[] groups = new String[]{"None", "Genre", "Title", "Author", "Count pages"};
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "Click on item "+which, Toast.LENGTH_SHORT).show();
                switch (which) {
                    case 0: {
                        Toast.makeText(context, "Order by " + groups[0], Toast.LENGTH_SHORT).show();
                        myViewModel.getListLiveDataBookItems().observe((LifecycleOwner) context, new Observer<List<BookItem>>() {
                            @Override
                            public void onChanged(List<BookItem> bookItems) {
                                bookListAdapter.setBooks(bookItems);
                            }
                        });
                        break;
                    }
                    case 1: {
                        Toast.makeText(context, "Order by " + groups[1], Toast.LENGTH_SHORT).show();
                        myViewModel.getListLiveDataBookItemsOrderByGenre().observe((LifecycleOwner) context, new Observer<List<BookItem>>() {
                            @Override
                            public void onChanged(List<BookItem> bookItems) {
                                bookListAdapter.setBooks(bookItems);
                            }
                        });
                        break;
                    }
                    case 2: {
                        Toast.makeText(context, "Order by " + groups[2], Toast.LENGTH_SHORT).show();
                        myViewModel.getListLiveDataBookItemsOrderByTitle().observe((LifecycleOwner) context, new Observer<List<BookItem>>() {
                            @Override
                            public void onChanged(List<BookItem> bookItems) {
                                bookListAdapter.setBooks(bookItems);
                            }
                        });
                        break;
                    }
                    case 3: {
                        Toast.makeText(context, "Order by " + groups[3], Toast.LENGTH_SHORT).show();
                        myViewModel.getListLiveDataBookItemsOrderByAuthor().observe((LifecycleOwner) context, new Observer<List<BookItem>>() {
                            @Override
                            public void onChanged(List<BookItem> bookItems) {
                                bookListAdapter.setBooks(bookItems);
                            }
                        });
                        break;
                    }
                    case 4: {
                        Toast.makeText(context, "Order by " + groups[4], Toast.LENGTH_SHORT).show();
                        myViewModel.getListLiveDataBookItemsOrderByCountPage().observe((LifecycleOwner) context, new Observer<List<BookItem>>() {
                            @Override
                            public void onChanged(List<BookItem> bookItems) {
                                bookListAdapter.setBooks(bookItems);
                            }
                        });
                        break;
                    }
                }
            }

        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Order by")
                .setItems(groups, onClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showDeleteGenreAlertDialog(final Activity context, final List<Genre> genreList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final int size = genreList.size();
        final String[] genres = new String[size];
        final boolean[] booleans = new boolean[size];

        for (int a = 0; a<size;a++) {
            genres[a] = genreList.get(a).name;
            booleans[a] = false;
        }
        DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    booleans[which] = isChecked;
            }
        };

        DialogInterface.OnClickListener onClickOkeyListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int a = 0; a < size; a++) {
                    if(booleans[a] ==true){
                        MyRepository.getInstance(context.getApplication()).deleteGenreById(genreList.get(a).id);
                    }
                }
                Toast.makeText(context, "Selected items deleted!", Toast.LENGTH_SHORT).show();
            }
        };



        builder.setTitle("Delete genres")
                .setMultiChoiceItems(genres, booleans, onMultiChoiceClickListener)
                .setPositiveButton("Ok", onClickOkeyListener)
                .setNegativeButton("Cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    int searchGenreIndex(List<Genre> genres, String genreName) {
        for (int a = 0; a < genres.size(); a++) {
            if (genres.get(a).name.equals(genreName)) {
                return a;
            }
        }
        return 0;
    }

    int searchGenreId(List<Genre> genres, String genreName) {
        for (Genre genre : genres) {
            if (genre.name.equals(genreName)) {
                return genre.id;
            }
        }
        return 0;
    }
}
