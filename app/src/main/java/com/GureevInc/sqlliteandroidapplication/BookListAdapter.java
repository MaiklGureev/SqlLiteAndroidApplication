package com.GureevInc.sqlliteandroidapplication;

import android.content.Context;
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

    private Context context;

    public BookListAdapter(Context context) {
        this.context = context;
    }

    void setBookAndGenreLists(List<BookItem> books, List<Genre> genres) {
        bookList = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.recycler_view_item_book, parent);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Click on item!",Toast.LENGTH_SHORT).show();
            }
        });
        BookViewHolder bookViewHolder = new BookViewHolder(itemView);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.textViewTitle.setText(bookList.get(position).title);
        holder.textViewAuthor.setText(bookList.get(position).author);
        holder.textViewCountPages.setText(bookList.get(position).countPages);

        holder.textViewId.setText(bookList.get(position).id);
        holder.textViewGenreId.setText(bookList.get(position).genreId);
        holder.textViewGenre.setText(bookList.get(position).genre);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

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
    }
}
