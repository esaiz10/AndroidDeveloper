package com.example.bookshelf.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookGridScreen(viewModel: BookViewModel) {
    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📚 Bookshelf") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text(text = "Search books...") }
            )
            Button(
                onClick = { viewModel.searchBooks(query) },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Search")
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(books) { book ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            AsyncImage(
                                model = book.thumbnailUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(180.dp)
                                    .fillMaxWidth()
                            )
                            Text(text = book.title)
                        }
                    }
                }
            }
        }
    }
}