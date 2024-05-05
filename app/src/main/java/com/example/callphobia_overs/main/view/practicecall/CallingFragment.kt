package com.example.callphobia_overs.main.view.practicecall

import android.os.SystemClock
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallingBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallingFragment : BaseFragment<FragmentCallingBinding>(R.layout.fragment_calling) {
    override fun initClick() {
        val navController = requireActivity().findNavController(R.id.fragContainer)

        binding.btnStopCall.setOnClickListener {
            navController.navigate(R.id.action_callingFragment_to_callingEndFragment)
        }
    }

    override fun initView() {
       callTimer()
    }

    private fun callTimer(){
        binding.timerCallTime.base = SystemClock.elapsedRealtime()
        binding.timerCallTime.start()
    }

    private fun toolbarShow(){
        val navController = findNavController()

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id == R.id.callingFragment)
                (requireActivity() as MainActivity).showToolbarView()
        }
    }
}