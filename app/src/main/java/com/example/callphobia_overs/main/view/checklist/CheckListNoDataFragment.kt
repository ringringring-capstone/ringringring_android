package com.example.callphobia_overs.main.view.checklist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCheckListNoDataBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.MainActivity

class CheckListNoDataFragment : BaseFragment<FragmentCheckListNoDataBinding>(R.layout.fragment_check_list_no_data) {
    override fun initView() {
        val name = (requireActivity() as MainActivity).getUserName()
        binding.textNoDataChecklist.text = "${name}님은 아직 검사 결과가 없어요 😓"
    }

    override fun initClick() {
        binding.btnGoCheckList.setOnClickListener {
            findNavController().navigate(R.id.action_checkListNoDataFragment_to_checkListTestFragment)
        }
    }

}