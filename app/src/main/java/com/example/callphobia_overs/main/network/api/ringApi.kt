package com.example.callphobia_overs.main.network.api

import com.example.callphobia_overs.main.network.models.Login
import com.example.callphobia_overs.main.network.models.LoginResponse
import com.example.callphobia_overs.main.network.models.MembershipResponse
import com.example.callphobia_overs.main.network.models.SendMembership
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


//서버 <-> 클라이언트 간의 통신 api 정의
interface RingApi {

    @POST("/login")/** 로그인 */
    @Headers("Content-Type: application/json")
    suspend fun login(
        @Body login : Login
    ) : Response<LoginResponse>

    @POST("/signup") /** 회원가입 */
    suspend fun joinMembership(
        @Body memberShip : SendMembership
    ) : Response<MembershipResponse>

    @GET("/emailcheck/{email}") /**이메일 유효 체크 */
    suspend fun checkMail(
        @Path("email") email : String
    ) : Response<Unit>

}