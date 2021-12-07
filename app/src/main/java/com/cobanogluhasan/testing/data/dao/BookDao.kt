package com.cobanogluhasan.testing.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cobanogluhasan.testing.data.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun observeBooks(): LiveData<List<Book>>
}