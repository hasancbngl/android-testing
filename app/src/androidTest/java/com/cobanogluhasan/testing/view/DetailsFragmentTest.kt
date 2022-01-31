package com.cobanogluhasan.testing.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.getOrAwaitValueTest
import com.cobanogluhasan.testing.launchFragmentInHiltContainer
import com.cobanogluhasan.testing.repo.FakeBookRepositoryAndroid
import com.cobanogluhasan.testing.ui.view.CustomFragmentFactory
import com.cobanogluhasan.testing.ui.view.fragments.DetailsFragment
import com.cobanogluhasan.testing.ui.view.fragments.DetailsFragmentDirections
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
class DetailsFragmentTest {

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
    fun navigateFromDetailsFragmentToSearchApiFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<DetailsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(ViewMatchers.withId(R.id.container)).perform(click())
        verify(navController).navigate(DetailsFragmentDirections.actionDetailsFragmentToSearchApiFragment())
    }


    @Test
    fun testSave() {
        val testViewModel = BookViewModel(FakeBookRepositoryAndroid())
        launchFragmentInHiltContainer<DetailsFragment>(
            factory = fragmentFactory
        ) {
            //  create late init mockviewmodel and use it on button click
            // mockViewModel = testViewModel
        }

        onView(ViewMatchers.withId(R.id.nameEditText)).perform(replaceText("insancıklar"))
        onView(ViewMatchers.withId(R.id.yearEdittext)).perform(replaceText("2012"))
        onView(ViewMatchers.withId(R.id.authorEdittext)).perform(replaceText("Dostoyesvki"))

        onView(ViewMatchers.withId(R.id.saveBtn)).perform(click())
        assertThat(testViewModel.bookList.getOrAwaitValueTest()).contains(
            Book(
                name = "insancıklar",
                year = 2012,
                author = "Dostoyesvki", imageUrl = ""
            )
        )
    }
}