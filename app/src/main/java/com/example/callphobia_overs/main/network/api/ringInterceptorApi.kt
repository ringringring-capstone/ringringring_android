package com.example.callphobia_overs.main.network.api

import com.example.callphobia_overs.main.network.models.SaveWeekCallTimeResponse
import com.example.callphobia_overs.main.network.models.SendSaveWeekCallTime
import com.example.callphobia_overs.main.network.models.WeeklyStatistics
import dagger.Module
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path


/**JWT 토큰이 필요한 api 모음*/
interface RingInterceptorApi{

    /**통화 시간 저장*/
    @PUT("/save")
    @Headers("Content-Type: application/json")
    suspend fun saveCallTime(
        @Body sendSaveWeekCallTime: SendSaveWeekCallTime
    ) : Response<SaveWeekCallTimeResponse>


    /**메인화면 -> 주간 연습 통계 api*/
    @GET("/usage/{email}")
    @Headers("Content-Type: application/json")
    suspend fun weekPracticeTime(
        @Path("email") email : String
    ) : Response<WeeklyStatistics>

}