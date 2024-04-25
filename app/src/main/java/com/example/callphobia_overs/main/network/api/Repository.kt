package com.example.callphobia_overs.main.network.api

import android.util.Log
import com.example.callphobia_overs.main.network.models.Login
import com.example.callphobia_overs.main.network.models.LoginResponse
import javax.inject.Inject

class Repository @Inject constructor(private val api : RingApi){

    suspend fun loginServer(userId : String, userPw : String) : Result<LoginResponse>{
        val result = api.login(Login(userId, userPw))

        if(result.isSuccessful){
            Log.d("repository","통신성공")
        }

        return Result.Error(result.message())
    }

}