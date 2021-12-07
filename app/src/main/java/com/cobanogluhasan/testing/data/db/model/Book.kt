package com.cobanogluhasan.testing.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    var name: String,
    var author: String,
    var year: Int,
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)