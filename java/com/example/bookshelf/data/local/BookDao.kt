package com.example.bookshelf.data.local

import androidx.room.*
import com.example.bookshelf.model.BookEntity

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Query("DELETE FROM books")
    suspend fun clearAll()
}