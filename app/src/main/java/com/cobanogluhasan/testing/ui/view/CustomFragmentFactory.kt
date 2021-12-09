package com.cobanogluhasan.testing.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.cobanogluhasan.testing.ui.adapters.BookRecyclerAdapter
import com.cobanogluhasan.testing.ui.adapters.ImageRecyclerAdapter
import com.cobanogluhasan.testing.ui.view.fragments.DetailsFragment
import com.cobanogluhasan.testing.ui.view.fragments.MainFragment
import com.cobanogluhasan.testing.ui.view.fragments.SearchApiFragment
import dagger.Component
import javax.inject.Inject

/**
 * Override the fragment factory in order to use dependency, values in fragments constructor
 */

class CustomFragmentFactory @Inject constructor(
    private val imageAdapter:ImageRecyclerAdapter,
    private val bookAdapter: BookRecyclerAdapter,
    private val glide: RequestManager,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MainFragment::class.java.name -> MainFragment(bookAdapter)
            DetailsFragment::class.java.name -> DetailsFragment(glide)
            SearchApiFragment::class.java.name -> SearchApiFragment(imageAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}