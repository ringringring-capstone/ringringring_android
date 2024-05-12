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

    private var codeCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initClick()
        textWatcher()

    }

    private fun initView(){
        if(codeCheck){ //메일 인증 완료 되었을때만, 버튼 활성화 시키기
            binding.btnMembership.setBackgroundResource(R.drawable.background_btn_maincolor)
            binding.btnMembership.isEnabled = true
        }
    }

    private fun initClick(){

        with(binding){
            groupMembershipIdpw.btnDuplicationCheck.setOnClickListener { //이메일로 코드 보내기
                val email = groupMembershipIdpw.etUserEmail.text.toString()
                var result = false

                CoroutineScope(Dispatchers.IO).launch {
                    if(email.isNotEmpty()){
                        result = vm.memberShipMailCheck(email)
                    }

                    if(result){
                        runOnUiThread {
                            Toast.makeText(this@membershipActivity, "입력하신 이메일로 인증코드를 보내드렸어요.", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Log.d(LOG, "이메일 인증코드 보내기 실패")
                    }
                }
            }

            groupMembershipIdpw.btnEmailCodeCheck.setOnClickListener { //이메일 코드값 인증
                val email = groupMembershipIdpw.etUserEmail.text.toString()
                val code = groupMembershipIdpw.etUserEmailCode.text.toString()
                var result = false

                CoroutineScope(Dispatchers.IO).launch {
                    if(email.isNotEmpty() && code.isNotEmpty()){
                        result = vm.memberShipMailCodeCheck(email, code.toInt())
                    }

                    if(result){
                        runOnUiThread {
                            codeCheck = true
                            Toast.makeText(this@membershipActivity, "이메일 인증이 완료되었어요.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.d(LOG,"이메일 인증 실패")
                    }
                }
            }

            if(codeCheck){
                btnMembership.setOnClickListener { //회원가입 버튼 클릭시
                    val name = groupMembershipName.etUserName.text.toString()
                    val email = groupMembershipIdpw.etUserEmail.text.toString()
                    val pw = groupMembershipIdpw.etUserPwd.text.toString()

                    CoroutineScope(Dispatchers.IO).launch {
                        val result = vm.memberShip(name, email, pw)

                        if(result){
                            runOnUiThread {
                                val intent = Intent(this@membershipActivity, membershipComplete::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }

        binding.btnBackLogin.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun textWatcher(){
        with(binding){
            groupMembershipName.etUserName.addTextChangedListener ( object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    if (s.isNullOrEmpty()) {
                        groupMembershipName.textLayoutName.error = "이름을 입력해주세요."
                    } else if(s.length < 2){
                        groupMembershipName.textLayoutName.error = "이름은 두 글자 이상이어야 해요."
                    } else{
                        groupMembershipName.textLayoutName.error = null
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            }) //사용자 이름


            groupMembershipIdpw.etUserEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        groupMembershipIdpw.textLayoutEmail.error = "이메일을 입력해주세요."
                    } else {
                        groupMembershipIdpw.textLayoutEmail.error = null
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    val emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                    val pattern = Pattern.compile(emailPattern)
                    val checkResult = pattern.matcher(s.toString())

                    if(!checkResult.find()){
                        groupMembershipIdpw.textLayoutEmail.error = "이메일 형식에 맞춰 작성해주세요."
                    } else {
                        groupMembershipIdpw.textLayoutEmail.error = null
                    }
                }
            }) //이메일

            groupMembershipIdpw.etUserEmailCode.addTextChangedListener (object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        groupMembershipIdpw.textLayoutEmailCode.error = "입력하신 이메일로 보내드린 인증코드를 적어주세요."
                    } else {
                        groupMembershipIdpw.textLayoutEmailCode.error = null
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }) //인증코드

            groupMembershipIdpw.etUserPwd.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        groupMembershipIdpw.textLayoutPw.error = ""
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    val pwdPattern =  "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$"
                    val pattern = Pattern.compile(pwdPattern) //패턴 생성
                    val checkResult = pattern.matcher(s.toString())

                    if(!checkResult.find()){
                        groupMembershipIdpw.textLayoutPw.error = "8자리 이상 영문,숫자,특수문자 조합으로 입력해주세요."
                    } else {
                        groupMembershipIdpw.textLayoutPw.error = null
                    }
                }

            }) //비밀번호 입력

            groupMembershipIdpw.etUserPwdCheck.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(groupMembershipIdpw.etUserPwd.text.toString() != s.toString()){
                        groupMembershipIdpw.textLayoutPwCheck.error = "비밀번호가 일치하지 않아요."
                    } else {
                        groupMembershipIdpw.textLayoutPwCheck.error = null
                    }
                }
            }) //비밀번호 재확인


        } //with
    }
}