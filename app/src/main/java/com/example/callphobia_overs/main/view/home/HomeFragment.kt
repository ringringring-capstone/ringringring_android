package com.example.callphobia_overs.main.view.home

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentHomeBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.practicecall.CallingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    override fun initClick() { //뭘 누르던간 다 전화통화 화면으로 이동해야함

        binding.groupHomeMenu.btnReservation.setOnClickListener { //예약
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_homeFragment_to_callingFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_callingFragment)
        }

        binding.groupHomeMenu.btnDelivery.setOnClickListener { //배달
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_homeFragment_to_callingFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_callingFragment)
            //NavHostFragment.findNavController(CallingFragment())
        }

        binding.groupHomeMenu.btnInquiry.setOnClickListener { //상담
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_homeFragment_to_callingFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_callingFragment)
        }

        binding.groupHomeMenu.btnMisson.setOnClickListener { //미션버튼
            Log.d("미션버튼 누름","미션버튼")
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_homeFragment_to_missionMainFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_missionMainFragment)
        }

    }

    override fun initView() {

    }

}