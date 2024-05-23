package com.example.callphobia_overs.main.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**주간통계 부분*/
@Keep
data class WeeklyStatistics(
    @SerializedName("duration") //누적시간
    val duration : Int,
    @SerializedName("average")
    val average : Int,
    @SerializedName("message")
    val message : String
)
