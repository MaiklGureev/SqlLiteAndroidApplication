package com.GureevInc.sqlliteandroidapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.BookItem;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private List<BookItem> bookList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();
    private AlertDialogActions alertDialogActions = new AlertDialogActions();

    private Activity context;

    public BookListAdapter(Activity context) {
        this.context = context;
    }

    void setBooks(List<BookItem> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_book, parent,false);
        final BookViewHolder bookViewHolder = new BookViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogActions.showMainAlertDialogForItem(context,bookViewHolder.getBookItem(),genreList);
            }
        });
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        String title = bookList.get(position).title;
        String author = bookList.get(position).author;
        String genre = bookList.get(position).genre;
        int countPages = bookList.get(position).countPages;
        int id= bookList.get(position).id;

        int genreId = bookList.get(position).genreId;

        holder.textViewTitle.setText("Title: "+title);
        holder.textViewAuthor.setText("Author: "+author);
        holder.textViewCountPages.setText("Count pages: "+countPages);

        holder.textViewId.setText(String.valueOf(id));
        holder.textViewGenreId.setText(String.valueOf(genreId));
        holder.textViewGenre.setText("Genre: "+genre);

        holder.setBookItem(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private BookItem bookItem;

        private final TextView textViewId;
        private final TextView textViewGenreId;
        private final TextView textViewTitle;
        private final TextView textViewAuthor;
        private final TextView textViewGenre;
        private final TextView textViewCountPages;

        private BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewGenreId = itemView.findViewById(R.id.textViewGenreId);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewCountPages = itemView.findViewById(R.id.textViewCountPages);
        }

        public BookItem getBookItem() {
            return bookItem;
        }

        public void setBookItem(BookItem bookItem) {
            this.bookItem = bookItem;
        }
    }
}
