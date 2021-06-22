package com.example.androiddemo.ui.lumperList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.databinding.RecycelrviewLumperBinding

class LumperListAdapter :
    RecyclerView.Adapter<LumperListAdapter.LumpersViewHolder>() {

    private val lumperList: ArrayList<Data> = ArrayList()
    override fun getItemCount(): Int {
        return lumperList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LumpersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycelrview_lumper,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LumpersViewHolder, position: Int) {
        holder.recycelrViewLumperBinding.lumper = lumperList[position]
    }

    inner class LumpersViewHolder(val recycelrViewLumperBinding: RecycelrviewLumperBinding) :
        RecyclerView.ViewHolder(recycelrViewLumperBinding.root)


    fun updateArrayList(lumperDataList: List<Data>) {
        lumperList.clear()
        lumperList.addAll(lumperDataList)
        notifyDataSetChanged()
    }
}