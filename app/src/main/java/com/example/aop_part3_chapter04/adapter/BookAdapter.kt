package com.example.aop_part3_chapter04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aop_part3_chapter04.R
import com.example.aop_part3_chapter04.model.Book

class BookAdapter(
    val books: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookItemViewHolder>() {
    inner class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitleTextView: TextView
        val bookDescriptionTextView : TextView
        val coverImgView : ImageView

        init {
            bookTitleTextView = itemView.findViewById(R.id.title_textview)
            bookDescriptionTextView = itemView.findViewById(R.id.description_textview)
            coverImgView = itemView.findViewById(R.id.cover_imgview)
        }

        fun bindViews(book: Book) {
            bookTitleTextView.text = book.title
            bookDescriptionTextView.text = book.description
            Glide
                .with(coverImgView.context)
                .load(book.coverImgUri)
                .into(coverImgView)
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
