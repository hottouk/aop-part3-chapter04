package com.example.aop_part3_chapter04

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.aop_part3_chapter04.model.Book

class DetailActivity : AppCompatActivity() {
    private val titleTextView: TextView by lazy {
        findViewById(R.id.title_detail_textview)
    }
    private val coverImgView: ImageView by lazy {
        findViewById(R.id.cover_detail_imageview)
    }
    private val descriptionTextView: TextView by lazy {
        findViewById(R.id.description_detail_textview)
    }
    private val reviewEditTextView: EditText by lazy {
        findViewById(R.id.review_detail_edtextview)
    }

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "BookSearchHistoryDB"
        ).build()

        val model = intent.getParcelableExtra<Book?>("book")
        titleTextView.text = model?.title.orEmpty()
        descriptionTextView.text = model?.description.orEmpty()

        Glide
            .with(coverImgView.context)
            .load(model?.coverImgUri.orEmpty())
            .into(coverImgView)
    }
}