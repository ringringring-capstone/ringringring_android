package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**전화 통화 시간 저장, 주간 통계 부분*/
@Keep
data class SendSaveWeekCallTime(
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("callTime")
    val callTime : Int
)

@Keep
data class SaveWeekCallTimeResponse(
    @SerializedName("callTime")
    val callTime: Int,
    @SerializedName("message")
    val message : String
)

