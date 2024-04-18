package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.callphobia_overs.databinding.ActivityLoginFragmentBinding
import com.example.callphobia_overs.main.view.MainActivity

class loginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoMembership.setOnClickListener {
            val intent = Intent(this, membershipActivity::class.java)
            startActivity(intent)
        }
    }
}