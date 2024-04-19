package com.example.callphobia_overs.main.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentHomeBinding
import com.example.callphobia_overs.main.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initClick() {
        binding.groupHomeMenu.btnReservation

        binding.groupHomeMenu.btnDelivery

        binding.groupHomeMenu.btnInquiry

    }

    override fun initView() {

    }

}