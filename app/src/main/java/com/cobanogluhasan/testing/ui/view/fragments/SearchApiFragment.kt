package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding
import com.cobanogluhasan.testing.databinding.FragmentSearchApiBinding

class SearchApiFragment : Fragment(R.layout.fragment_search_api) {

    private var binding: FragmentSearchApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchApiBinding.bind(view)
    }
}