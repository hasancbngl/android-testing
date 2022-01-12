package com.cobanogluhasan.testing.viewmodel

import com.cobanogluhasan.testing.repo.FakeBookRepository
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import org.junit.Before

class BookViewModelTest {

    private lateinit var viewModel: BookViewModel

    @Before
    fun setup() {
        viewModel = BookViewModel(FakeBookRepository())
    }
}