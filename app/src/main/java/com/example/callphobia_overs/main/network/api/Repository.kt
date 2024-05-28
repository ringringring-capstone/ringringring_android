package com.example.callphobia_overs.main.network.api

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.callphobia_overs.main.core.Application
import com.example.callphobia_overs.main.network.models.EmailCodeCheck
import com.example.callphobia_overs.main.network.models.Login
import com.example.callphobia_overs.main.network.models.LoginResponse
import com.example.callphobia_overs.main.network.models.MembershipResponse
import com.example.callphobia_overs.main.network.models.SaveWeekCallTimeResponse
import com.example.callphobia_overs.main.network.models.SendMembership
import com.example.callphobia_overs.main.network.models.SendSaveWeekCallTime
import com.example.callphobia_overs.main.network.models.WeeklyStatistics
import com.example.callphobia_overs.main.network.models.retrofitModel
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords
import com.example.callphobia_overs.main.network.models.roomDB.CallRecordsDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**어떤 retrofit을 사용하는지 명시해줘야함 -> @retrofitModel.NoInterceptorRetrofit 이 부분들*/
class Repository @Inject constructor(@retrofitModel.NoInterceptorRetrofit private val api : RingApi,
                                     @retrofitModel.InterceptorRetrofit private val interceptorApi: RingInterceptorApi,
                                     private val callDao: CallRecordsDao) {

    private val LOG = "repository"

    suspend fun loginServer(userId: String, userPw: String) : Result<LoginResponse> {
        val result = api.login(Login(userId, userPw))
        Log.d(LOG, "사용자아이디, 비번 : $userId, $userPw")

        if (result.isSuccessful) {
            Log.d(LOG, "로그인 통신성공")
            Application.preferManager.token =
                result.headers()["Authorization"]?.replace("Bearer","")?.trim()
            val data = Application.preferManager.token
            Log.d(LOG,"jwt토큰$data")
            return Result.Success(result.body()!!)
        }
        Log.d(LOG, "로그인 통신실패")
        return Result.Error(result.message())
    }

    /**이메일 인증 코드*/
    suspend fun memberShipEmailCheck(userEmail: String) : Result<Unit> {
        val result = api.checkMail(userEmail)

        if(result.isSuccessful){
            Log.d(LOG,"이메일로 코드 보내기 성공")
            return Result.Success(Unit)
        }
        return Result.Error(result.message())
    }

    /**이메일 인증코드 확인 */
    suspend fun memberShipEmailCodeCheck(userEmail: String, userCode : Int) : Result<Unit> {
        val result = api.checkCode(EmailCodeCheck(userEmail, userCode))

        if(result.isSuccessful){
            Log.d(LOG,"인증 완료")
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

    /**통화 시간 서버로 보내기*/
    suspend fun saveCallTime(userId: Int, callTime : Int) : Result<SaveWeekCallTimeResponse>{
        val result = interceptorApi.saveCallTime(SendSaveWeekCallTime(userId, callTime))

        if(result.isSuccessful){
            Log.d(LOG,"서버로 통화시간 보내기 성공")
            return Result.Success(result.body()!!)
        }
        Log.d(LOG,"서버로 통화시간 보내기 실패")
        return Result.Error(result.message())
    }

    /**주간 통계 불러오기*/

    suspend fun weekPracticeTime(email : String) : Result<WeeklyStatistics> {
        val result = interceptorApi.weekPracticeTime(email)

        if(result.isSuccessful){
            Log.d(LOG,"주간통계 가져오기 성공")
            return Result.Success(result.body()!!)
        }
        Log.d(LOG,"주간통계 가져오기 실패")
        return Result.Error(result.message())
    }

    /**AI 부분*/
    suspend fun testAi(id : Int) : Result<Unit> {
        val result = interceptorApi.testAi(id)

        if(result.isSuccessful){
            Log.d(LOG, "ai api 임시 작성 요청 완료")
            return Result.Success(Unit)
        }

        Log.d(LOG, "ai api 임시 작성 요청 완료")
        return Result.Error(result.message())
    }

    suspend fun reservationAI(userSay : String) : Result<String> {
        val result = interceptorApi.reservationAI(userSay)

        if(result.isSuccessful){
            Log.d(LOG, "응답내용 : " + result.body().toString())
            return Result.Success(result.body()!!)
        }

        Log.d(LOG, "예약 인공지능과 연결 실패")
        return Result.Error(result.message())
    }


    /**DB 관련 부분*/

    fun callRecordInsert(callRecords: CallRecords) {
        callDao.insertCall(callRecords)
    }

    fun callRecordDelete(callRecords: CallRecords){
        callDao.delete(callRecords)
    }

    fun callRecordAllData() : LiveData<List<CallRecords>> {
        return callDao.getAll()
    }

}

