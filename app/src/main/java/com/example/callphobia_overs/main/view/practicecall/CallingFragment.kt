package com.example.callphobia_overs.main.view.practicecall

import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallingBinding
import com.example.callphobia_overs.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallingFragment : BaseFragment<FragmentCallingBinding>(R.layout.fragment_calling) {
    override fun initClick() {
        binding.btnTest.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun initView() {

    }
}