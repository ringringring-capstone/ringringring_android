package com.example.callphobia_overs.main.view.checklist

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentChecklistTestBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.core.Application
import com.example.callphobia_overs.main.view.adapter.checkDataClass
import com.example.callphobia_overs.main.view.adapter.checklistAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckListTestFragment : BaseFragment<FragmentChecklistTestBinding>(R.layout.fragment_checklist_test) {

    private var checkCount = 0
    override fun initClick() {

    }

    override fun initView() {
        var contentList = arrayListOf<checkDataClass>(
            checkDataClass(resources.getString(R.string.checklist_one)),
            checkDataClass(resources.getString(R.string.checklist_two)),
            checkDataClass(resources.getString(R.string.checklist_three)),
            checkDataClass(resources.getString(R.string.checklist_four)),
            checkDataClass(resources.getString(R.string.checklist_five)),
            checkDataClass(resources.getString(R.string.checklist_six)),
            checkDataClass(resources.getString(R.string.checklist_seven)),
        )
        val adapter = checklistAdapter(requireContext(), contentList)

        binding.listviewChecklist.adapter = adapter

        binding.btnChecklistResult.setOnClickListener {
            checkCount = adapter.getCheckedCount()
            Log.d("checkCount개수",checkCount.toString())

            Application.preferManager.setIsCheck("checkList",true)
            Application.preferManager.setCheckListPerCent("percent", checkCount)
            findNavController().navigate(R.id.checkListResultFragment)
        }
    }

}