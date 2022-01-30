package com.cobanogluhasan.testing.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.launchFragmentInHiltContainer
import com.cobanogluhasan.testing.ui.view.CustomFragmentFactory
import com.cobanogluhasan.testing.ui.view.fragments.DetailsFragment
import com.cobanogluhasan.testing.ui.view.fragments.DetailsFragmentDirections
import com.cobanogluhasan.testing.ui.view.fragments.MainFragment
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
        Espresso.onView(ViewMatchers.withId(R.id.container)).perform(click())
        verify(navController).navigate(DetailsFragmentDirections.actionDetailsFragmentToSearchApiFragment())
    }

    @Test
    fun testOnBackPressedNavigateToMainFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<DetailsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()
        verify(navController).popBackStack()
    }
}