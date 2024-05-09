package com.example.callphobia_overs.main.view.setting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallRecordsBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import com.example.callphobia_overs.main.view.adapter.CallRecordsAdapter
import kotlinx.coroutines.launch

class CallRecordsFragment : BaseFragment<FragmentCallRecordsBinding>(R.layout.fragment_call_records) {

    private val vm : DataViewModel by activityViewModels()
    override fun initView() {
        val adapter = CallRecordsAdapter(requireContext())
        binding.recyclerCallRecords.adapter = adapter
        binding.recyclerCallRecords.layoutManager = LinearLayoutManager(context)

        vm.callRecordAll().observe(this, Observer {
            adapter.setData(it)
        })

    }

    override fun initClick() {

    }

}