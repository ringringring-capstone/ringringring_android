package com.example.callphobia_overs.main.core

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


/**체크 리스트 확인 용도*/
class SharedPreference (context: Context) {
    private val pref = context.getSharedPreferences("checkList", Context.MODE_PRIVATE)
    private val percentPerf = context.getSharedPreferences("percent", Context.MODE_PRIVATE)

    private val jwtTokenPerf = context.getSharedPreferences("jwtToken", Context.MODE_PRIVATE)

    var token : String?
        get() = jwtTokenPerf.getString("jwtToken", null)
        set(value){
            jwtTokenPerf.edit().putString("jwtToken",value).apply()
        }

    fun getIsCheck(key : String) : Boolean {
        return pref.getBoolean(key,false) //기본으로 false 가져옴
    }

    fun setIsCheck(key : String, defValue: Boolean){
        pref.edit().putBoolean(key, defValue).apply()
    }

    fun getCheckListPerCent(key : String) : Int {
        return percentPerf.getInt(key, 0)
    }

    fun setCheckListPerCent(key : String, setValue : Int){
        percentPerf.edit().putInt(key, setValue).apply()
    }
}