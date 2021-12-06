package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        binding?.apply {
            rootLayout?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
            fab.setOnClickListener {
                MainFragmentDirections.actionMainFragmentToDetailsFragment()
            }
        }
    }
}