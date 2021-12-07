package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)
        binding?.apply {
            saveBtn.setOnClickListener {
                findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToSearchApiFragment()
                )
            }
        }

        //nav components do this already but we can use it
        //in case we wanna add something more
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}