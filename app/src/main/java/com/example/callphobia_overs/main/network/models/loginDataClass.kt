package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginDataClass(
    @SerializedName("email")
    val email : String
)
