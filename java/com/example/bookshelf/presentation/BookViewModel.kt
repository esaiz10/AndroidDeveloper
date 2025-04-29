package com.example.bookshelf.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.model.Book
import com.example.bookshelf.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        searchBooks("android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val booksList = repository.getBooks(query)
                println("Fetched ${booksList.size} books")  // 👈 Add this line
                _books.value = booksList
            } catch (e: Exception) {
                e.printStackTrace()
                _books.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }


}