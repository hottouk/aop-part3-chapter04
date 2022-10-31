package com.example.aop_part3_chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aop_part3_chapter04.adapter.BookAdapter
import com.example.aop_part3_chapter04.api.BookService
import com.example.aop_part3_chapter04.model.Book
import com.example.aop_part3_chapter04.model.BookSearchDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val bookRecyclerView: RecyclerView by lazy {
        findViewById(R.id.book_recycler_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)
        bookService.getBooksByName(
            "20gUNDqtYcE2fjtSb5OJ",
            "oJ1lbJub00",
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
                    Log.d(TAG, it.toString())
                    it.books.forEach { book ->
                        Log.d(TAG, book.toString())
                    }
                    attachBookAdapter(it.books)
                }
            }

            override fun onFailure(call: Call<BookSearchDto>, t: Throwable) { //실패
                Log.e(TAG, t.toString())

            }
        })
    }

    private fun attachBookAdapter(books: List<Book>) {
        val adapter = BookAdapter(books)
        bookRecyclerView.adapter = adapter
        bookRecyclerView.layoutManager = LinearLayoutManager(this)

    }


    companion object {
        const val TAG = "mainn"
    }
}