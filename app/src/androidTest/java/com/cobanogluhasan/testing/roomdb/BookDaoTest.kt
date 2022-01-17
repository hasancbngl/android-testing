package com.cobanogluhasan.testing.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.cobanogluhasan.testing.data.db.BookDatabase
import com.cobanogluhasan.testing.data.db.dao.BookDao
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class BookDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var bookDb: BookDatabase
    private lateinit var dao: BookDao

    @Before
    fun setup() {
        bookDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = bookDb.bookDao()
    }

    @After
    fun tearDown() {
        bookDb.close()
    }

    @Test
    fun insertBookTest() = runBlockingTest {
        val demoBook = Book("book","test",1970,"")
        dao.insertBook(demoBook)
        val list = dao.observeBooks().getOrAwaitValueTest()
        assertThat(list.size).isEqualTo(1)
    }

    @Test
    fun deleteBookTest() = runBlockingTest {
       // dao.deleteBook()
    }
}