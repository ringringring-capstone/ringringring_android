package com.example.callphobia_overs.main.view.setting

import androidx.navigation.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentSettingBinding
import com.example.callphobia_overs.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initClick() {
        binding.btnCallingHistory.setOnClickListener {
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_settingFragment_to_callRecordsFragment)
        }
    }

    override fun initView() {

    }
}