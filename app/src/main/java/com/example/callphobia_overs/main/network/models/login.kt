package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Login( //클라 -> 서버
    @SerializedName("email")
    val email : String,
    @SerializedName("pwd")
    val pwd : String
)

@Keep
data class LoginResponse( // 서버 -> 클라
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("pwd")
    val pwd: String
)
