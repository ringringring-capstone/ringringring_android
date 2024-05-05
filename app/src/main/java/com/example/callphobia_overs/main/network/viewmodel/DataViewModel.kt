package com.example.callphobia_overs.main.network.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callphobia_overs.main.network.api.Repository
import com.example.callphobia_overs.main.network.api.Result
import com.example.callphobia_overs.main.network.models.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**retrofit 호출 로직
 * 참고 : https://zks145.tistory.com/46*/
@HiltViewModel
class DataViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {

    private val LOG = "viewModel"

    suspend fun login(userEmail : String, userPw : String) : Boolean {
        val result = repository.loginServer(userEmail, userPw)
        Log.d(LOG, "로그인 성공여부 : " + (result is Result.Success).toString())
        return result is Result.Success
    }

    suspend fun memberShipMailCheck(userEmail: String) : Boolean {
        val result = repository.memberShipEmailCheck(userEmail)
        return result is Result.Success
    }

    suspend fun memberShip(userName : String, userEmail: String, userPw: String) : Boolean {
        val result = repository.memberShipServer(userName, userEmail, userPw)
        Log.d(LOG, "회원가입 성공여부 : " + (result is Result.Success).toString())
        return result is Result.Success
    }
}