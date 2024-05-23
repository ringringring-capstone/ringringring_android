package com.example.callphobia_overs.main.view.checklist

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCheckListResultBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.core.Application
import com.example.callphobia_overs.main.view.MainActivity
import com.example.callphobia_overs.main.view.practicecall.CallingEndFragmentArgs
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckListResultFragment : BaseFragment<FragmentCheckListResultBinding>(R.layout.fragment_check_list_result) {
    private val args : CheckListResultFragmentArgs by navArgs()
    private val LOG = "checkListResult"

    override fun initClick() {
        binding.btnCheckState.setOnClickListener {
            Log.d("checklist","테스트버튼 클릭")
            findNavController().navigate(R.id.action_checkListResultFragment_to_checkListTestFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {

        checkBanner((requireActivity() as MainActivity).getUserName())
        val percent = Application.preferManager.getCheckListPerCent("percent")

        binding.groupRecentResult.progressBarUserStatus.setProgress(percent, true)
        binding.groupRecentResult.checklistProgressPercent.text = "${percent * 10}%"

        chartUserPercent()

        Log.d(LOG,args.checkListResult)

    }

    private fun checkBanner(name : String) {
        binding.groupRecentResult.textChecklistTitle.text = "${name}님의 최근 결과예요."
        binding.groupChecklistPeopleResult.textChecklistPeoplechartTitle.text = "👥 ${name}님과 비슷한 사람들은?"
    }

    private fun chartUserPercent(){
        val pieChart : PieChart = binding.groupChecklistPeopleResult.chartsUserPercentage

        val chartData = ArrayList<PieEntry>()
        chartData.add(PieEntry(20F, "양호수준"))
        chartData.add(PieEntry(30F, "주의수준"))
        chartData.add(PieEntry(50F, "심각수준"))

        val chartColors = listOf(
            ContextCompat.getColor(requireContext(), R.color.text_color_gray),
            ContextCompat.getColor(requireContext(), R.color.text_color_gray),
            ContextCompat.getColor(requireContext(), R.color.sub_color)
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
}