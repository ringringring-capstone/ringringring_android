package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMembershipFragmentBinding
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

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
            val cName = checkName()
            val cPwd = checkPwd()
            val cPwdV = checkPwdValid()
            val cEmail = checkEmail()

            if(cName && cPwd && cPwdV && cEmail){
                val name = binding.etUserName.text.toString()
                val email = binding.etUserEmail.text.toString()
                val pw = binding.etUserPwd.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val result = vm.memberShip(name, email, pw)

                    if(result){
                        runOnUiThread {
                            Log.d(LOG, "회원가입 성공")
                            val intent = Intent(this@membershipActivity, membershipComplete::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }

    }

    private fun checkName() : Boolean { //이름 확인

        with(binding) {
            if(etUserName.text?.isEmpty() == true){
                textLayoutName.error = "이름을 입력해주세요."
                return false

            } else if (etUserName.text.toString().length < 2){
                textLayoutName.error = "이름을 2글자 이상 입력해주세요."
                return false

            } else {
                textLayoutName.error = null
                return true
            }
        }

    }


    private fun checkPwd() : Boolean { //비밀번호 확인
        val pwdPattern =  "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$"
        val pattern = Pattern.compile(pwdPattern) //패턴 생성
        val checkResult = pattern.matcher(binding.etUserPwd.text.toString())

        with(binding){
           if(!checkResult.find()){
               textLayoutPw.error = "8자리 이상 영문,숫자,특수문자 조합으로 입력해주세요."
           } else {
               return true
           }
        }
        return false
    }

    private fun checkPwdValid() : Boolean {
        with(binding){
            if(etUserPwd.text.toString() != etUserPwdCheck.text.toString()){
                textLayoutPwCheck.error = "비밀번호가 올바르지 않아요."
                return false

            } else if(etUserPwdCheck.text.toString().isEmpty()) {
                textLayoutPwCheck.error = "입력했던 비밀번호를 작성해주세요."
                return false

            } else {
                textLayoutPwCheck.error = null
                return true
            }
        }
    }

    private fun checkEmail() : Boolean { //이메일 확인
        val emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(emailPattern)
        val checkResult = pattern.matcher(binding.etUserEmail.text.toString())

        with(binding){
            if(etUserEmail.text?.isEmpty() == true){
                binding.textLayoutEmail.error = "이메일을 입력해 주세요."
                return false

            } else if(!checkResult.find()){
                binding.textLayoutEmail.error = "이메일 형식에 맞춰 작성해 주세요."
                return false

            } else {
                binding.textLayoutEmail.error = null
                return true
            }
        }

    }
}