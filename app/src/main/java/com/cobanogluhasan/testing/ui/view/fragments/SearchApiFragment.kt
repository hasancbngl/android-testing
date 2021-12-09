package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding
import com.cobanogluhasan.testing.databinding.FragmentSearchApiBinding
import com.cobanogluhasan.testing.ui.adapters.ImageRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchApiFragment @Inject constructor(
    private val imageAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_search_api) {

    private var binding: FragmentSearchApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchApiBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}