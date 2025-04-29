package com.example.bookshelf.repository

import com.example.bookshelf.data.local.BookDao
import com.example.bookshelf.data.network.BookApiService
import com.example.bookshelf.model.Book
import com.example.bookshelf.model.toEntity
import com.example.bookshelf.model.toBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkBookRepository(
    private val api: BookApiService,
    private val dao: BookDao
) : BookRepository {

    override suspend fun getBooks(query: String): List<Book> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.searchBooks(query)

            val books: List<Book> = response.items?.mapNotNull { item ->
                val info = item.volumeInfo
                val id = item.id ?: return@mapNotNull null
                val title = info?.title ?: return@mapNotNull null
                val authors: String = info?.authors?.joinToString(", ") ?: "Unknown"
                val thumbnail = info?.imageLinks?.thumbnail?.replace("http", "https") ?: return@mapNotNull null

                Book(
                    id = id,
                    title = title,
                    authors = authors,
                    thumbnailUrl = thumbnail
                )
            } ?: emptyList()

            dao.clearAll()
            dao.insertAll(books.map { it.toEntity() })

            books
        } catch (e: Exception) {
            e.printStackTrace()
            dao.getAllBooks().map { it.toBook() }
        }
    }
}