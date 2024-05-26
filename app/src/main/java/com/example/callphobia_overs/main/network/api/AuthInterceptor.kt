package com.example.callphobia_overs.main.network.api

import android.util.Log
import com.example.callphobia_overs.main.core.Application
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {
    private val LOG = "interceptor"
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(LOG, "interceptor 접근")

        if(Application.preferManager.token == null){
            Log.d(LOG, "JWT토큰없음")
        }
        var intercept = chain.request().newBuilder().addHeader("Authorization",
            Application.preferManager.token ?: "").build()
        return chain.proceed(intercept)
    }
}