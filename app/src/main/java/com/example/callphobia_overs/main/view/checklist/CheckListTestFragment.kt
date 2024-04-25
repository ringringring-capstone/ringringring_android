package com.example.callphobia_overs.main.view.checklist

import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentChecklistTestBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.adapter.checkDataClass
import com.example.callphobia_overs.main.view.adapter.checklistAdapter

class CheckListTestFragment : BaseFragment<FragmentChecklistTestBinding>(R.layout.fragment_checklist_test) {
    override fun initClick() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        var contentList = arrayListOf<checkDataClass>(
            checkDataClass(R.string.checklist_one.toString()),
            checkDataClass(R.string.checklist_two.toString()),
            checkDataClass(R.string.checklist_three.toString()),
            checkDataClass(R.string.checklist_four.toString()),
            checkDataClass(R.string.checklist_five.toString()),
            checkDataClass(R.string.checklist_six.toString()),
            checkDataClass(R.string.checklist_seven.toString()),
        )

        val adapter = checklistAdapter(requireContext(), contentList)
        binding.listviewChecklist.adapter = adapter
    }

}