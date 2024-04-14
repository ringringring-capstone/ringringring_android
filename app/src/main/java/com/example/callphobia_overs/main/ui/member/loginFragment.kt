package com.example.callphobia_overs.main.ui.member

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.ActivityLoginFragmentBinding

class loginFragment : AppCompatActivity() {

    private lateinit var binding : ActivityLoginFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}