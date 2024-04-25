package com.example.callphobia_overs.main.view.home

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentHomeBinding
import com.example.callphobia_overs.main.base.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    lateinit var navController: NavController
    override fun initClick() { //뭘 누르던간 다 전화통화 화면으로 이동해야함
        binding.groupHomeMenu.btnReservation.setOnClickListener { //예약
            findNavController().navigate(R.id.callingFragment)
        }

        binding.groupHomeMenu.btnDelivery.setOnClickListener { //배달
            findNavController().navigate(R.id.callingFragment)
        }

        binding.groupHomeMenu.btnInquiry.setOnClickListener { //상담
            findNavController().navigate(R.id.callingFragment)
        }

        binding.groupHomeMenu.btnMisson.setOnClickListener { //미션버튼
            Log.d("미션버튼 누름","미션버튼")
            findNavController().navigate(R.id.missionMainFragment)
        }

    }

    override fun initView() {

    }


}