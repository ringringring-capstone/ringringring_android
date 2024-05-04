package com.example.callphobia_overs.main.network.models.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dagger.Provides


/**Data access object 실제 데이터 베이스에 접근하는 객체*/

@Dao
interface CallRecordsDao {
    @Insert
    fun insertCall(callRecords: CallRecords) //CallRecords data class 형식으로 저장할거임

    @Delete
    fun delete(callRecords: CallRecords)

    @Query("SELECT * FROM callrecords")
    fun getAll() : List<CallRecords>
}
