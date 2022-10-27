package com.example.aop_part3_chapter04.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {

    @GET("v1/search/book.json")
    fun getBooksByName(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") secretKey : String,
        @Query("query") keyword: String
    ): Call<>
    //<>에 모델데이터를 넣어야 함. call은 레트로핏에서 지원하는 특정 클래스임. 콜을 반환한다.
}