package com.cobanogluhasan.testing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cobanogluhasan.testing.MainCoroutineRule
import com.cobanogluhasan.testing.getOrAwaitValueTest
import com.cobanogluhasan.testing.repo.FakeBookRepository
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import com.cobanogluhasan.testing.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BookViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = BookViewModel(FakeBookRepository())
    }

    @Test
    fun `insert book without year returns error`() {
        viewModel.makeBook("dostoyesvki", bookName = "insancıklar", "a")
        val value = viewModel.insertBookmsg.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert book with year of string returns error`() {
        viewModel.makeBook("albert camus", "yabancı", "aaaa")
        val status = viewModel.insertBookmsg.getOrAwaitValueTest().status
        assertThat(status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert book without name returns error`() {
        viewModel.makeBook("albert camus", "", "1125")
        val status = viewModel.insertBookmsg.getOrAwaitValueTest().status
        assertThat(status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert book without author returns error`() {
        viewModel.makeBook("", "yabancı", "1234")
        val status = viewModel.insertBookmsg.getOrAwaitValueTest().status
        assertThat(status).isEqualTo(Status.ERROR)
    }
}