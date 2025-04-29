package com.example.bookshelf.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookshelf.di.AppModule

class BookViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(AppModule.provideBookRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}