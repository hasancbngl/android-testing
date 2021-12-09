package com.cobanogluhasan.testing.ui.view.fragments

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var swipeListener: ItemTouchHelper.SimpleCallback? = null

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

    private fun initSwipeListener(recyclerView: RecyclerView) {
        swipeListener = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val book = bookAdapter.differ.currentList[layoutPosition]
                viewModel.deleteBook(book)
            }
        }
        swipeListener?.let {
            ItemTouchHelper(it).attachToRecyclerView(recyclerView)
        }
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
            initSwipeListener(this)
        }
    }
}