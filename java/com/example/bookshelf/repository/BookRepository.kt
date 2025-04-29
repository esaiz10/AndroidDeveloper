package com.example.bookshelf.repository

import com.example.bookshelf.model.Book

interface BookRepository {
    suspend fun getBooks(query: String): List<Book>
}