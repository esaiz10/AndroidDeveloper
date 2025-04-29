package com.example.bookshelf.di

import android.content.Context
import com.example.bookshelf.data.local.BookDatabase
import com.example.bookshelf.data.network.BookApiService
import com.example.bookshelf.repository.BookRepository
import com.example.bookshelf.repository.NetworkBookRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bookshelf.data.local.BookDao

object AppModule {

    fun provideBookRepository(context: Context): BookRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BookApiService::class.java)
        val db = BookDatabase.getDatabase(context)
        return NetworkBookRepository(service, db.bookDao())
    }

    fun provideDatabase(context: Context): BookDatabase {
        return BookDatabase.getDatabase(context)
    }
}