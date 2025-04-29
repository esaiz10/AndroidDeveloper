package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bookshelf.presentation.BookGridScreen
import com.example.bookshelf.presentation.BookViewModel
import com.example.bookshelf.presentation.BookViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: BookViewModel = viewModel(factory = BookViewModelFactory(applicationContext))
            BookGridScreen(viewModel = viewModel)
        }
    }
}