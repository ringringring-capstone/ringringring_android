package com.example.callphobia_overs.main.view.setting

import android.annotation.SuppressLint
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallRecordsBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import com.example.callphobia_overs.main.view.adapter.CallRecordsAdapter

class CallRecordsFragment : BaseFragment<FragmentCallRecordsBinding>(R.layout.fragment_call_records) {

    private val vm : DataViewModel by activityViewModels()
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        val adapter = CallRecordsAdapter(requireContext())
        binding.recyclerCallRecords.adapter = adapter
        binding.recyclerCallRecords.layoutManager = LinearLayoutManager(context)

        vm.callRecordAll().observe(this, Observer {
            adapter.setData(it)
        })

        binding.toolbarRecords.run {
            title = "통화 내역 버튼"
            setNavigationIcon(R.drawable.btn_back_black)
        }

        deleteRecord(adapter)

    }

    override fun initClick() {

    }

    private fun deleteRecord(adapter: CallRecordsAdapter) {

    }

}