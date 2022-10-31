package com.example.aop_part3_chapter04.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aop_part3_chapter04.R
import com.example.aop_part3_chapter04.model.Book

class BookAdapter(
    val books: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookItemViewHolder>() {
    inner class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitleTextView: TextView

        init {
            bookTitleTextView = itemView.findViewById(R.id.title_textview)
        }

        fun bindViews(book: Book) {
            bookTitleTextView.text = book.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_book, parent, false)
        return BookItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val book: Book = books[position]
        holder.bindViews(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
