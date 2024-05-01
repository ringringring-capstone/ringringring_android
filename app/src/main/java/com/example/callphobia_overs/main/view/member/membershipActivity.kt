package com.example.callphobia_overs.main.view.member

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMembershipFragmentBinding
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class membershipActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMembershipFragmentBinding
    private val vm by viewModels<DataViewModel>()
    private val LOG = "membership"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMembership.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val email = binding.etUserEmail.text.toString()
            val pw = binding.etUserPwd.text.toString()

            if(name.isEmpty() || email.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = vm.memberShip(name, email, pw)

                    if(result){
                        runOnUiThread {
                            Toast.makeText(this@membershipActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            Log.d(LOG, "회원가입 성공")
                        }
                    }
                }
            }
        }

    }

    private fun checkEmail(email : String) {

    }
}