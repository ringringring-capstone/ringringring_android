package com.example.callphobia_overs.main.view.member

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityMembershipCompleteBinding

class membershipComplete : AppCompatActivity() {

    private lateinit var binding : ActivityMembershipCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCompleteLogin.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }
}