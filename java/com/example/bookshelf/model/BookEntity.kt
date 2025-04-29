package com.example.bookshelf.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String,
    val thumbnail: String
)

// Room → Domain
fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        authors = authors,
        thumbnailUrl = thumbnail
    )
}

// Domain → Room
fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        title = this.title,
        authors = this.authors,
        thumbnail = this.thumbnailUrl
    )
}