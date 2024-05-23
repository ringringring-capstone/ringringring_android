package com.example.callphobia_overs.main.view.practicecall

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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
    private val LOG = "callingEnd"
    private val vm : DataViewModel by activityViewModels()
    private val args : CallingEndFragmentArgs by navArgs()

    override fun initClick() {
        binding.btnCallendTohome.setOnClickListener {
            requireActivity().findNavController(R.id.fragContainer).navigate(R.id.action_callingEndFragment_to_homeFragment)
        }

        binding.btnSaveCallHistory.setOnClickListener {
            Log.d(LOG, args.callTitle)

            if(args.callContent.isNotEmpty()){
                lifecycleScope.launch(Dispatchers.IO) {
                    vm.callRecordInsert(CallRecords("자전거 환불 전화 (테스트데이터)", resources.getString(R.string.callRecord_content_test)))
                }
                Toast.makeText(requireContext(),"통화 내용을 저장했어요.", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(),"통화 내용한 내용이 없어 저장할 수 없어요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        Log.d(LOG, args.callTitle)
        Log.d(LOG, args.callContent)
        Log.d(LOG, args.callTime.toString())

        if(args.callTime < 60){ //통화 1분 미만일 때
            binding.textCallendTime.text = String.format("00:%02d",args.callTime)

        } else {
            val mm = args.callTime / 60 //분
            val ss = args.callTime % 60 //초

            binding.textCallendTime.text = String.format("%02d:%02d", mm, ss)

        }



    }
}