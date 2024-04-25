package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.callphobia_overs.databinding.ActivityLoginFragmentBinding
import com.example.callphobia_overs.main.network.api.Repository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class loginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginFragmentBinding
    @Inject lateinit var api : Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val userId = binding.textId.toString()
            val userPw = binding.textPassword.toString()

            CoroutineScope(Dispatchers.IO).launch {
                api.loginServer(userId, userPw)
            }
        }

        binding.btnGoMembership.setOnClickListener {
            val intent = Intent(this, membershipActivity::class.java)
            startActivity(intent)
        }
    }
}