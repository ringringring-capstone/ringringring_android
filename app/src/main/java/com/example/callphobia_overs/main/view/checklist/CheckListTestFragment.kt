package com.example.callphobia_overs.main.view.checklist

import android.util.Log
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentChecklistTestBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.adapter.checkDataClass
import com.example.callphobia_overs.main.view.adapter.checklistAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckListTestFragment : BaseFragment<FragmentChecklistTestBinding>(R.layout.fragment_checklist_test) {
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
        Log.d("체크리스트내용main", resources.getString(R.string.checklist_one))
        binding.listviewChecklist.adapter = adapter
    }

}