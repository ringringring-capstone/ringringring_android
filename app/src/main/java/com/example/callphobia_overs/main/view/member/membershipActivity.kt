package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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

    var name = ""
    var email = ""
    var pw = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = binding.etUserName.text.toString()
        email = binding.etUserEmail.text.toString()
        pw = binding.etUserPwd.text.toString()

        binding.run {
            textLayoutName.run {
                editText?.run {
                    addTextChangedListener {
                        if(name.isEmpty()){
                            textLayoutName.error = "이름을 입력해주세요."
                        } else {
                            textLayoutName.error = null
                        }
                    }
                }
            }
        }

        binding.btnMembership.setOnClickListener {

            if(name.isEmpty() || email.isEmpty() || pw.isEmpty()){
                Toast.makeText(this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = vm.memberShip(name, email, pw)

                    if(result){
                        runOnUiThread {
                            //Toast.makeText(this@membershipActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            Log.d(LOG, "회원가입 성공")
                            val intent = Intent(this@membershipActivity, membershipComplete::class.java)
                            startActivity(intent)

                        }
                    }
                }
            }
        }

    }

    private fun checkName() {

    }

    private fun checkEmail(email : String) : Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        if(email.isEmpty()){
            binding.textLayoutEmail.error = "메일을 입력해주세요."

        } else if(!email.matches(emailPattern.toRegex())){
            binding.textLayoutEmail.error = "이메일 형식에 맞춰 작성해주세요."

        } else {
            binding.textLayoutEmail.error = null
            binding.textLayoutEmail.isErrorEnabled = false
            return true
        }

        return false
    }
}