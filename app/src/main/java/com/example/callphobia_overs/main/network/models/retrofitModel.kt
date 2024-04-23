package com.example.callphobia_overs.main.network.models

import com.example.callphobia_overs.main.network.api.RingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**참고 블로그 : https://jtm0609.tistory.com/22*/

@Module
@InstallIn(SingletonComponent::class)
object retrofitModel {

    private val baseUrl = "http://localhost:9090" //4.14일 기준 임시로 작성해둠

    private val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY //okhttp 로그 추가
    }

    @Singleton
    @Provides
    fun OkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun ringRetrofit() : RingApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(baseUrl)
            .build()
            .create(RingApi::class.java)
    }

}