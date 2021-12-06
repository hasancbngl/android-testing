package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)
    }
}