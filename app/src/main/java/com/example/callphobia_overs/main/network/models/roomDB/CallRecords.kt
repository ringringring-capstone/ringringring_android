package com.example.callphobia_overs.main.network.models.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CallRecords(
    var title : String, //통화 연습 주제
    var records : String //통화 연습 내용
){
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}
