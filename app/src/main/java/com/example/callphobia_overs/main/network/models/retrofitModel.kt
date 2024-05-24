package com.example.callphobia_overs.main.network.models

import com.example.callphobia_overs.main.network.api.RingApi
import com.example.callphobia_overs.main.network.api.RingInterceptorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

/**참고 블로그 : https://jtm0609.tistory.com/22
 * https://velog.io/@galaxy/Hilt-Qualifier
 * */

@Module
@InstallIn(SingletonComponent::class)
object retrofitModel {

    private val baseUrl = "http://3.36.125.87:9090" //4.14일 기준 임시로 작성해둠

    private val logging = HttpLoggingInterceptor().apply{
        level = HttpLoggingInterceptor.Level.BODY //okhttp 로그 추가
    }

    /** JWTToken이 필요한 api와, 그렇지 않은 api 연결을 위해
     * Qualifier를 사용해 구분짓기 위한 annotation 클래스 생성*/
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NoInterceptorOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NoInterceptorRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorRetrofit

    /** OkHTTP에 각 annotaion을 붙여주어 구분짓도록 하기 */
    @NoInterceptorOkHttpClient
    @Singleton
    @Provides
    fun OkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @InterceptorOkHttpClient
    @Singleton
    @Provides
    fun OkHttpClientInterceptor() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    @NoInterceptorRetrofit
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

    @InterceptorRetrofit
    @Singleton
    @Provides
    fun ringInterceptorRetrofit() : RingInterceptorApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(baseUrl)
            .build()
            .create(RingInterceptorApi::class.java)
    }

}