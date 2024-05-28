package com.example.callphobia_overs.main.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.RowCallingHistoryBinding
import com.example.callphobia_overs.main.network.models.roomDB.CallRecords
import com.example.callphobia_overs.main.network.viewmodel.DataViewModel

/**roomDB에서 데이터 전부 가져오기 참고 : https://bumjae.tistory.com/47#AddActivity-1 */

interface DeleteListener{
    fun delete(item : CallRecords)
}

class CallRecordsAdapter(val context : Context,
    private val deleteListener: DeleteListener) : RecyclerView.Adapter<CallRecordsAdapter.Holder>() { //dataclass 따로 만들 필요 없이, 그냥 DB에 사용했던거 쓰면 됨

    private var dataList : List<CallRecords> = listOf()
    private val LOG = "callRecordApdater"

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

            binding.btnCallhistoryOption.setOnClickListener {
                val popup = PopupMenu(binding.root.context, it)
                popup.menuInflater.inflate(R.menu.callhistory_menu_option, popup.menu)

                popup.setOnMenuItemClickListener {menuItem ->
                    when(menuItem.itemId){
                        R.id.option_delete -> {
                            Log.d(LOG, "삭제버튼 클릭")
                            deleteListener.delete(item)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
                popup.show()
            }

        }

    }
}