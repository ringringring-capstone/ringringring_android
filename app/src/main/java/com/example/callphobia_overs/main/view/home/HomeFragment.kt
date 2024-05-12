package com.example.callphobia_overs.main.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentHomeBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.network.api.Repository
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel
import com.example.callphobia_overs.main.view.MainActivity
import com.example.callphobia_overs.main.view.practicecall.CallingFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val vm : DataViewModel by activityViewModels()
    @Inject lateinit var repository : Repository
    private val LOG = "homefragment"

    override fun initClick() { //뭘 누르던간 다 전화통화 화면으로 이동해야함

        val navController = requireActivity().findNavController(R.id.fragContainer)

        binding.groupHomeMenu.btnReservation.setOnClickListener { //예약
            navController.navigate(R.id.action_homeFragment_to_callingFragment)
        }

        binding.groupHomeMenu.btnDelivery.setOnClickListener { //배달
            navController.navigate(R.id.action_homeFragment_to_callingFragment)
        }

        binding.groupHomeMenu.btnInquiry.setOnClickListener { //상담
            navController.navigate(R.id.action_homeFragment_to_callingFragment)
        }

        binding.groupHomeMenu.btnMisson.setOnClickListener { //미션버튼
            Log.d("미션버튼 누름","미션버튼")
            navController.navigate(R.id.action_homeFragment_to_missionMainFragment)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        showBanner((requireActivity() as MainActivity).getUserName())
        bottomViewShow()
        weekChartShow()
        monthChartShow()
        audioPermission()
    }

    private fun showBanner(name : String){
        binding.groupHomeBanner.textUserWelcome.text = "${name}님,\n안녕하세요 😊"
    }

    private fun bottomViewShow(){
        val navController = findNavController()

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id == R.id.homeFragment || destination.id == R.id.settingFragment || destination.id == R.id.checkListResultFragment)
                (requireActivity() as MainActivity).showBottomNavigationView()
            else
                (requireActivity() as MainActivity).hideBottomNavigationView()
        }
    }



    private fun weekChartShow(){
        val pieChart : PieChart = binding.homeMyPracticeChart.chartsMyPracticeWeek

        val chartData = ArrayList<PieEntry>()
        chartData.add(PieEntry(20F, "양호수준"))
        chartData.add(PieEntry(30F, "주의수준"))
        chartData.add(PieEntry(50F, "심각수준"))

        val chartColors = listOf(
            ContextCompat.getColor(requireContext(), R.color.background_chart_green),
            ContextCompat.getColor(requireContext(), R.color.background_chart_orange),
            ContextCompat.getColor(requireContext(), R.color.background_chart_pink)
        )

        val dataSet = PieDataSet(chartData, "")
        dataSet.colors = chartColors
        dataSet.valueTextSize = 16F
        dataSet.valueTextColor = R.color.black

        val setWeekChart = PieData(dataSet)
        pieChart.data = setWeekChart

        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false
        pieChart.animateY(1000)

        pieChart.invalidate()
    }

    private fun monthChartShow(){
        val pieChart : PieChart = binding.homeMyPracticeChart.chartsMyPracticeMonth

        val chartData = ArrayList<PieEntry>()
        chartData.add(PieEntry(20F, "양호수준"))
        chartData.add(PieEntry(30F, "주의수준"))
        chartData.add(PieEntry(50F, "심각수준"))

        val chartColors = listOf(
            ContextCompat.getColor(requireContext(), R.color.background_chart_green),
            ContextCompat.getColor(requireContext(), R.color.background_chart_orange),
            ContextCompat.getColor(requireContext(), R.color.background_chart_pink)
        )

        val dataSet = PieDataSet(chartData, "")
        dataSet.colors = chartColors
        dataSet.valueTextSize = 16F
        dataSet.valueTextColor = R.color.black

        val setWeekChart = PieData(dataSet)
        pieChart.data = setWeekChart

        pieChart.legend.isEnabled = false
        pieChart.description.isEnabled = false
        pieChart.animateY(1000)

        pieChart.invalidate()
    }

    private fun audioPermission(){ //stt 사용해야 해서 권한 확인 필요
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }
    }

}