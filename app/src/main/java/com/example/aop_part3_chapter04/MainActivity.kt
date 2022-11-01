package com.example.aop_part3_chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.aop_part3_chapter04.adapter.BookAdapter
import com.example.aop_part3_chapter04.adapter.HistoryAdapter
import com.example.aop_part3_chapter04.api.BookService
import com.example.aop_part3_chapter04.model.Book
import com.example.aop_part3_chapter04.model.BookSearchDto
import com.example.aop_part3_chapter04.model.History
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val bookRecyclerView: RecyclerView by lazy {
        findViewById(R.id.book_recycler_view)
    }
    val historyRecyclerView: RecyclerView by lazy {
        findViewById(R.id.history_recycler_view)
    }
    val searchEditTextView: EditText by lazy {
        findViewById(R.id.search_edit_textview)
    }

    private lateinit var bookService: BookService
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //룸DB
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "BookSearchHistoryDB"
        ).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)
        bookService.getBooksByName(
            getString(R.string.naver_API_ID),
            getString(R.string.naver_API_SECRETKEY),
            "꿈"
        ).enqueue(object : Callback<BookSearchDto> {
            override fun onResponse(
                call: Call<BookSearchDto>,
                response: Response<BookSearchDto>
            ) { //성공
                if (!response.isSuccessful) {
                    return
                }
                response.body()?.let {
                    attachBookAdapter(it.books)
                }
            }

            override fun onFailure(call: Call<BookSearchDto>, t: Throwable) { //실패
                Log.e(TAG, t.toString())
            }
        })
        initSearchEditText()
    }

    private fun search(keyword: String) {
        bookService.getBooksByName(
            getString(R.string.naver_API_ID),
            getString(R.string.naver_API_SECRETKEY),
            keyword
        )
            .enqueue(object : Callback<BookSearchDto> {
                override fun onResponse(
                    call: Call<BookSearchDto>,
                    response: Response<BookSearchDto>
                ) {
                    saveSearchKeyword(keyword)
                    hideHistoryRecyclerView()
                    if (!response.isSuccessful) {
                        return
                    }
                    response.body()?.let {
                        attachBookAdapter(it.books)
                    }

                }

                override fun onFailure(call: Call<BookSearchDto>, t: Throwable) {
                    Log.e(TAG, t.toString())
                    hideHistoryRecyclerView()
                }
            })
    }

    private fun attachBookAdapter(books: List<Book>) {
        val adapter = BookAdapter(books)
        bookRecyclerView.adapter = adapter
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun attachHistoryAdapter(keywordsHistory: List<History>) {
        val adapter = HistoryAdapter(keywordsHistory, historyDeleteClickedListener = { keyword ->
            deleteSearchKeyword(keyword)
        })
        historyRecyclerView.adapter = adapter
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initSearchEditText() {
        Log.d(TAG, "showHistoryView 시험")
        searchEditTextView.setOnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(searchEditTextView.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        searchEditTextView.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryRecyclerView()
            }
                return@setOnTouchListener false
        }
    }

    private fun saveSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().insertHistory(History(null, keyword))
        }.start()
    }

    private fun deleteSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().delete(keyword)
            showHistoryRecyclerView()
        }.start()
    }

    //HistoryView 보이기
    private fun showHistoryRecyclerView() {
        Log.d(TAG, "showHistoryView 시험")
        Thread {
            val keywordsHistory = db.historyDao().getAll().reversed()
            runOnUiThread {
                attachHistoryAdapter(keywordsHistory)
            }
        }.start()
        historyRecyclerView.isVisible = true
    }

    //HistoryView 숨기기
    private fun hideHistoryRecyclerView() {
        historyRecyclerView.isVisible = false
    }


    companion object {
        const val TAG = "mainn"

    }
}