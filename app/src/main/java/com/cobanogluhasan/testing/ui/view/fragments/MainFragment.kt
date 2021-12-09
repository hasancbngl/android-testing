package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentMainBinding
import com.cobanogluhasan.testing.ui.adapters.BookRecyclerAdapter
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment @Inject constructor(
    private val bookAdapter: BookRecyclerAdapter
) : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null
    private val viewModel: BookViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        observeLiveData()
        setClickListeners()
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observeLiveData() {
        viewModel.bookList.observe(viewLifecycleOwner, {
            bookAdapter.differ.submitList(it)
        })
    }

    private fun setClickListeners() {
        binding?.fab?.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailsFragment()
            )
        }
    }

    private fun initRecycler() {
        binding?.recyclerview?.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}