package com.example.callphobia_overs.main.view.checklist

import android.content.Intent
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCheckListResultBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckListResultFragment : BaseFragment<FragmentCheckListResultBinding>(R.layout.fragment_check_list_result) {
    override fun initClick() {
        binding.btnCheckState.setOnClickListener {
            Log.d("checklist","테스트버튼 클릭")
            findNavController().navigate(R.id.action_checkListResultFragment_to_checkListTestFragment)
        }
    }

    override fun initView() {
        checkBanner((requireActivity() as MainActivity).getUserName())
    }

    private fun checkBanner(name : String) {
        binding.groupRecentResult.textChecklistTitle.text = "${name}님의 최근 결과예요."
        binding.groupChecklistPeopleResult.textChecklistPeoplechartTitle.text = "👥 ${name}님과 비슷한 사람들은?"
    }
}