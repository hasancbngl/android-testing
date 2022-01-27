package com.cobanogluhasan.testing.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.cobanogluhasan.testing.data.db.BookDatabase
import com.cobanogluhasan.testing.data.db.dao.BookDao
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class BookDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDb")
    lateinit var bookDb: BookDatabase
    private lateinit var dao: BookDao

    /* before using hilt
      private lateinit var bookDb: BookDatabase

    @Before
    fun setup() {
        bookDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = bookDb.bookDao()
    } */

    @Before
    fun setup() {
        hiltRule.inject()
        dao = bookDb.bookDao()
    }

    @After
    fun tearDown() {
        bookDb.close()
    }

    @Test
    fun insertBookTest() = runBlockingTest {
        val demoBook = Book("book", "test", 1970, "", 1)
        dao.insertBook(demoBook)
        val list = dao.observeBooks().getOrAwaitValueTest()
        assertThat(list).contains(demoBook)
    }

    @Test
    fun deleteBookTest() = runBlockingTest {
        val demoBook = Book("book", "test", 1970, "", 1)
        dao.insertBook(demoBook)
        dao.deleteBook(demoBook)
        val list = dao.observeBooks().getOrAwaitValueTest()
        assertThat(list).doesNotContain(demoBook)
    }
}