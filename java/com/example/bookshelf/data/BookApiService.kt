package com.example.bookshelf.data.network

import com.example.bookshelf.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BookResponse
}