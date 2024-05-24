package com.example.callphobia_overs.main.network.api

import com.example.callphobia_overs.main.network.models.EmailCodeCheck
import com.example.callphobia_overs.main.network.models.Login
import com.example.callphobia_overs.main.network.models.LoginResponse
import com.example.callphobia_overs.main.network.models.MembershipResponse
import com.example.callphobia_overs.main.network.models.SaveWeekCallTimeResponse
import com.example.callphobia_overs.main.network.models.SendMembership
import com.example.callphobia_overs.main.network.models.SendSaveWeekCallTime
import com.example.callphobia_overs.main.network.models.WeeklyStatistics
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


/**JWT 토큰이 필요하지 않은 API 정의*/
interface RingApi {

    /** 로그인 */
    @POST("/login")
    @Headers("Content-Type: application/json")
    suspend fun login(
        @Body login : Login
    ) : Response<LoginResponse>

    /** 회원가입 */
    @POST("/signup")
    @Headers("Content-Type: application/json")
    suspend fun joinMembership(
        @Body memberShip : SendMembership
    ) : Response<MembershipResponse>

    /**이메일로 인증번호 보내기 */
    @GET("/mailsender/{email}")
    @Headers("Content-Type: application/json")
    suspend fun checkMail(
        @Path("email") email : String
    ) : Response<Unit>

    /**메일 인증 코드 유효성 검사*/
    @POST("/codecheck")
    @Headers("Content-Type: application/json")
    suspend fun checkCode(
        @Body emailCodeCheck: EmailCodeCheck
    ) : Response<Unit>



}