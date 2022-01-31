package com.cobanogluhasan.testing.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.getOrAwaitValueTest
import com.cobanogluhasan.testing.launchFragmentInHiltContainer
import com.cobanogluhasan.testing.repo.FakeBookRepositoryAndroid
import com.cobanogluhasan.testing.ui.adapters.ImageRecyclerAdapter
import com.cobanogluhasan.testing.ui.view.CustomFragmentFactory
import com.cobanogluhasan.testing.ui.view.fragments.SearchApiFragment
import com.cobanogluhasan.testing.ui.view.fragments.SearchApiFragmentDirections
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchApiFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: CustomFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testSelectedImageUrl() {
        val navController = mock(NavController::class.java)
        val selectedImageUrl = "com.cobanoglu.hasan"
        val testViewModel = BookViewModel(FakeBookRepositoryAndroid())

        launchFragmentInHiltContainer<SearchApiFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            //imageAdapter.differ.submitList(listOf(selectedImageUrl))
            //  mockViewModel = testViewModel
        }

        Espresso.onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ViewHolder>(0, click())
        )
        verify(navController).navigate(SearchApiFragmentDirections.actionSearchApiFragmentToDetailsFragment())
        assertThat(testViewModel.selectedImageUrl.getOrAwaitValueTest()).isEqualTo(selectedImageUrl)
    }
}