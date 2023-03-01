package com.example.ussdaplication.presentation.ui.sms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.App
import com.example.ussdaplication.databinding.ItemSmsBinding
import com.example.ussdaplication.domain.model.sms.GetSmsModel

class SmsAdapter(private val context: Context) :
    RecyclerView.Adapter<SmsAdapter.ViewHolder>() {

    private val list = mutableListOf<GetSmsModel>()

    fun setList(data: List<GetSmsModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetSmsModel) -> Unit)? = null

    fun setItemClickListener(f: (id: GetSmsModel) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemSmsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetSmsModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSmsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}