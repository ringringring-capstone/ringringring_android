package com.example.callphobia_overs.main.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.callphobia_overs.databinding.RowCallingHistoryBinding
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords

/**roomDB에서 데이터 전부 가져오기 참고 : https://bumjae.tistory.com/47#AddActivity-1 */

class CallRecordsAdapter(val context : Context) : RecyclerView.Adapter<CallRecordsAdapter.Holder>() { //dataclass 따로 만들 필요 없이, 그냥 DB에 사용했던거 쓰면 됨

    private var dataList : List<CallRecords> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallRecordsAdapter.Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowCallingHistoryBinding.inflate(layoutInflater)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) { //여기서 클릭 리스너 설정
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(callRecords : List<CallRecords>){
        this.dataList = callRecords
        notifyDataSetChanged()
    }

    inner class Holder(val binding : RowCallingHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : CallRecords){
            binding.titleCallRecords.text = item.title
            binding.contentCallRecords.text = item.records
        }

    }
}