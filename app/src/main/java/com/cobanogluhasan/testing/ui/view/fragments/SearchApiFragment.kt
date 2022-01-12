package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.engine.Resource
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding
import com.cobanogluhasan.testing.databinding.FragmentSearchApiBinding
import com.cobanogluhasan.testing.ui.adapters.ImageRecyclerAdapter
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import com.cobanogluhasan.testing.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.security.auth.login.LoginException

private const val TAG = "SearchApiFragment"

@AndroidEntryPoint
class SearchApiFragment @Inject constructor(
    private val imageAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_search_api) {

    private var binding: FragmentSearchApiBinding? = null
    private val viewModel: BookViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchApiBinding.bind(view)
        lifecycleScope.launch {
            obverseData()
            initRecyclerAdapter()
            initSearchImage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initSearchImage() {
        lifecycleScope.launch {
            delay(1200)
            binding?.searchEdittext?.addTextChangedListener {
                if (it?.isNotEmpty() == true) viewModel.searchImage(it.toString())
            }
        }
    }

    private fun initRecyclerAdapter() {
        binding?.recycler?.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        imageAdapter.setOnItemClickListener {
            viewModel.setSelectedImageUrl(it)
            findNavController().popBackStack()
        }
    }

    private fun obverseData() {
        viewModel.imageList.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    val list = response.data?.hits?.map {
                        it.previewURL
                    }
                    imageAdapter.differ.submitList(list)
                    binding?.progressBar?.visibility = View.GONE
                }
                Status.ERROR -> {
                    Log.i(
                        TAG,
                        "obverseData: Error Occured, ${response.status}, ${response.message}"
                    )
                    binding?.progressBar?.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    Log.i(TAG, "obverseData: Loading")
                }
            }
        })
    }
}