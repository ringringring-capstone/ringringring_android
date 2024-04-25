package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/** 회원가입 */
@Keep
data class SendMembership( //서버로 보낼 데이터
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("pwd")
    val pwd : String
)

data class MembershipResponse( //서버에서 받을 데이터
    @SerializedName("date")
    val date : String,
    @SerializedName("message") //Success or Fail
    val message : String
)
