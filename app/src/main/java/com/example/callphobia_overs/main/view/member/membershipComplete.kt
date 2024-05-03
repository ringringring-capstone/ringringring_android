package com.example.callphobia_overs.main.view.member

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
        setContentView(R.layout.activity_membership_complete)

    }
}