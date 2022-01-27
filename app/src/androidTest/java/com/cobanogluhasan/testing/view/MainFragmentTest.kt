package com.cobanogluhasan.testing.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.launchFragmentInHiltContainer
import com.cobanogluhasan.testing.ui.view.CustomFragmentFactory
import com.cobanogluhasan.testing.ui.view.fragments.MainFragment
import com.cobanogluhasan.testing.ui.view.fragments.MainFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: CustomFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromMainToDetails() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<MainFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
        Mockito.verify(navController)
            .navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment())
    }
}