package com.example.callphobia_overs.main.network.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callphobia_overs.main.network.api.Repository
import com.example.callphobia_overs.main.network.api.Result
import com.example.callphobia_overs.main.network.models.LoginResponse
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**retrofit 호출 로직
 * 참고 : https://zks145.tistory.com/46*/
@HiltViewModel
class DataViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application) {
    private val LOG = "viewModel"

    /** viewmodel 내에서 직접적으로 데이터 바꿀 때 */
    private var _currentUserName = MutableLiveData<String>()
    private var _currentUserId = MutableLiveData<Int>()


    /** 외부에서 값에 접근하고 싶을 때 */
    val userName : LiveData<String>
        get() = _currentUserName


    /**초기값*/
    init{
        _currentUserName.postValue("이름없음")
    }


    /** 서버 관련 함수들 */
    suspend fun login(userEmail : String, userPw : String) : Boolean {
        val result = repository.loginServer(userEmail, userPw)

        when(result){
            is Result.Success -> { //사용자 정보 저장해주기
                val data = result.data
                Log.d(LOG, "사용자 이름 : ${data.name}")
                _currentUserName.postValue(data.name)
                _currentUserId.postValue(data.id)
                Log.d(LOG, "로그인 성공")
            }

            is Result.Error -> Log.d(LOG, "로그인 실패")
            is Result.Exception -> Log.d(LOG,"예외 발생")
        }

        Log.d(LOG, "저장된이름"+ _currentUserName.value.toString())

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


    /** DB 부분 */
    fun callRecordInsert(callRecords: CallRecords) {
        Log.d(LOG,"insert 접근")
        repository.callRecordInsert(callRecords)
    }

    fun callRecordDelete(callRecords: CallRecords){
        repository.callRecordDelete(callRecords)
    }

    fun callRecordAll() : LiveData<List<CallRecords>> {
        Log.d(LOG,"대화목록 모두 가져오기 접근")
        return repository.callRecordAllData()
    }

}