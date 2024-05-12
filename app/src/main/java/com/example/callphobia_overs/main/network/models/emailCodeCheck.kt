package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class EmailCodeCheck(
    @SerializedName("email")
    val email : String,
    @SerializedName("code")
    val code : Int
)
