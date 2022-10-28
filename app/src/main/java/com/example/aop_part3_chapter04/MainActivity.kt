package com.example.aop_part3_chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aop_part3_chapter04.api.BookService
import com.example.aop_part3_chapter04.model.BookSearchDto
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
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
        ).enqueue(object : Callback<BookSearchDto>{
            override fun onResponse(call: Call<BookSearchDto>, response: Response<BookSearchDto>) {
                TODO("Not yet implemented") //성공
            }

            override fun onFailure(call: Call<BookSearchDto>, t: Throwable) {
                TODO("Not yet implemented") //실패

            }
        })
    }
}