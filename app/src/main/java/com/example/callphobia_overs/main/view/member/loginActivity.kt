package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.callphobia_overs.databinding.ActivityLoginFragmentBinding
import com.example.callphobia_overs.main.network.api.Repository
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import com.example.callphobia_overs.main.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class loginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginFragmentBinding
    private val vm by viewModels<DataViewModel>()
    private val LOG = "loginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val userId = binding.textId.text.toString()
            val userPw = binding.textPassword.text.toString()

            Log.d(LOG, "사용자아이디 : $userId")
            Log.d(LOG, "사용자비번 : $userPw")

            if(userId.isEmpty() || userPw.isEmpty()){
                Toast.makeText(this,"아이디, 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show()

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d(LOG, "로그인 버튼 눌림")
                    val result = vm.login(userId, userPw)

                    if(result){
                        val intent = Intent(this@loginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

        binding.btnGoMembership.setOnClickListener {
            val intent = Intent(this, membershipActivity::class.java)
            startActivity(intent)
        }
    }
}