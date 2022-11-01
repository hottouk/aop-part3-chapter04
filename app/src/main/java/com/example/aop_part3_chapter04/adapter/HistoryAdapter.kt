package com.example.aop_part3_chapter04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aop_part3_chapter04.R
import com.example.aop_part3_chapter04.model.History

class HistoryAdapter(
    val keywordsHistory: List<History>,
    val historyDeleteClickedListener: (String) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder>() {
    inner class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val keywordTextView: TextView
        val historyClearBtn: ImageButton

        init {
            keywordTextView = itemView.findViewById(R.id.history_keyword_textview)
            historyClearBtn = itemView.findViewById(R.id.history_keyword_delete_btn)
        }

        fun bindViews(keyword: History) {
            keywordTextView.text = keyword.keyword
            historyClearBtn.setOnClickListener {
                historyDeleteClickedListener(keyword.keyword.orEmpty())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_history, parent, false)
        return HistoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val keyword = keywordsHistory[position]
        return holder.bindViews(keyword)
    }

    override fun getItemCount(): Int {
        return keywordsHistory.size
    }
}