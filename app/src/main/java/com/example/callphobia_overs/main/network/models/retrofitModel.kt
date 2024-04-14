package com.example.callphobia_overs.main.network.models

import com.example.callphobia_overs.main.network.api.RingApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object retrofitModel {

    private val baseUrl = "http://localhost:9090" //4.14일 기준 임시로 작성해둠

    private val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY //okhttp 로그 추가
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RingApi::class.java)

}