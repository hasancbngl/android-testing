package com.cobanogluhasan.testing.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.databinding.RecyclerBookRowBinding
import javax.inject.Inject

class BookRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookRecyclerAdapter.ViewHolder = ViewHolder(
        RecyclerBookRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: BookRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: RecyclerBookRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            with(binding) {
                author.text = book.author
                name.text = book.name
                year.text = book.year.toString()
                glide.load(book.imageUrl).into(container)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffUtil)
}