package com.example.callphobia_overs.main.network.api

import com.example.callphobia_overs.main.network.models.MembershipResponse
import com.example.callphobia_overs.main.network.models.SendMembership
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


//서버 <-> 클라이언트 간의 통신 api 정의
interface RingApi {
    @POST("signup") /** 회원가입 */
    fun JoinMembership(@Body memberData : SendMembership) : Call<MembershipResponse>

}