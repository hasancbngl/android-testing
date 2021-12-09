package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.databinding.FragmentDetailsBinding
import com.cobanogluhasan.testing.ui.viewmodel.BookViewModel
import com.cobanogluhasan.testing.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null
    private val viewModel: BookViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)
        initClickListeners()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initClickListeners() {
        binding?.apply {
            saveBtn.setOnClickListener {
                viewModel.makeBook(
                    authorEdittext.text.toString(),
                    nameEditText.text.toString(),
                    yearEdittext.text.toString()
                )
            }
            container.setOnClickListener {
                findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToSearchApiFragment()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, { url ->
            binding?.apply { glide.load(url).into(container) }
        })

        viewModel.insertBookmsg.observe(viewLifecycleOwner, {
            var message = ""
            when (it.status) {
                Status.SUCCESS -> {
                    message = "Success!"
                    findNavController().popBackStack()
                }
                Status.ERROR -> message = it.message.toString()
                Status.LOADING -> message =  "Loading"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
    }
}