package com.example.callphobia_overs.main.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.RowChecklistBinding

class checklistAdapter (val context: Context, val checkList: ArrayList<checkDataClass>): BaseAdapter() {
    private var binding : RowChecklistBinding? = null
    override fun getCount(): Int {
        return checkList.size
    }

    override fun getItem(position: Int): Any {
        return checkList[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        binding = RowChecklistBinding.inflate(LayoutInflater.from(context))
        val data = checkList[position]
        binding!!.rowChecklistContent.text = data.content
        Log.d("체크리스트내용",data.content)

        return binding!!.root

    }

}