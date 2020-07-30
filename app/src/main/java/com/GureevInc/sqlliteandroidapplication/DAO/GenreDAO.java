package com.GureevInc.sqlliteandroidapplication.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.GureevInc.sqlliteandroidapplication.entities.Book;
import com.GureevInc.sqlliteandroidapplication.entities.Genre;

import java.util.List;

@Dao
public interface GenreDAO {

    @Query("select * from genres")
    LiveData<List<Genre>> getAllGenres();

    @Query("select * from genres where id = :id")
    Genre getGenreById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllGenres (Genre ... genres);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGenres(Genre ... genres);

    @Delete(entity = Genre.class)
    void deleteGenres(Genre genre);

    @Query("delete from genres where genres.id = :id")
    void deleteGenresById(int id);

    @Query("delete from genres")
    void clear();

}
