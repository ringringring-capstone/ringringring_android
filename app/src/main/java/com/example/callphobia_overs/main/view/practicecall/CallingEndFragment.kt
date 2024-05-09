package com.example.callphobia_overs.main.view.practicecall

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallingEndBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CallingEndFragment : BaseFragment<FragmentCallingEndBinding>(R.layout.fragment_calling_end) {
    private val vm : DataViewModel by activityViewModels()

    override fun initClick() {
        binding.btnCallendTohome.setOnClickListener {
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_callingEndFragment_to_homeFragment)
        }

        binding.btnSaveCallHistory.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                vm.callRecordInsert(CallRecords("자전거 환불 전화 (테스트데이터)", resources.getString(R.string.callRecord_content_test)))
            }
        }
    }

    override fun initView() {

    }
}