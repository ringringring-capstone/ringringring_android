package com.example.callphobia_overs.main.network.api

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.callphobia_overs.main.network.models.Login
import com.example.callphobia_overs.main.network.models.LoginResponse
import com.example.callphobia_overs.main.network.models.MembershipResponse
import com.example.callphobia_overs.main.network.models.SendMembership
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords
import com.example.callphobia_overs.main.network.models.roomDB.CallRecordsDao
import javax.inject.Inject

class Repository @Inject constructor(private val api : RingApi, private val callDao: CallRecordsDao) {

    private val LOG = "repository"

    suspend fun loginServer(userId: String, userPw: String) : Result<LoginResponse> {
        val result = api.login(Login(userId, userPw))
        Log.d(LOG, "사용자아이디, 비번 : $userId, $userPw")
        //val result = api.login(userId, userPw)

        if (result.isSuccessful) {
            Log.d(LOG, "로그인 통신성공")
            return Result.Success(result.body()!!)
        }

        Log.d(LOG, "로그인 통신실패")
        return Result.Error(result.message())
    }

    suspend fun memberShipEmailCheck(userEmail: String) : Result<Unit> {
        val result = api.checkMail(userEmail)

        if(result.isSuccessful){
            return Result.Success(Unit)
        }
        return Result.Error(result.message())
    }

    suspend fun memberShipServer(userName: String, userEmail: String, userPw: String): Result<MembershipResponse> {
        val result = api.joinMembership(SendMembership(userName, userEmail, userPw))

        if(result.isSuccessful){
            Log.d(LOG,"회원가입성공")
            return Result.Success(result.body()!!)
        }

        Log.d(LOG, "회원가입 실패")
        return Result.Error(result.message())
    }

    /**DB 관련 부분*/

    fun callRecordInsert(callRecords: CallRecords) {
        callDao.insertCall(callRecords)
    }

    fun callRecordDelete(callRecords: CallRecords){
        callDao.insertCall(callRecords)
    }

    fun callRecordAllData() : LiveData<List<CallRecords>> {
        return callDao.getAll()
    }

}

