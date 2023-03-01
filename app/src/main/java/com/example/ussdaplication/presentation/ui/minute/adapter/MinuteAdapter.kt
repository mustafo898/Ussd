package com.example.ussdaplication.presentation.ui.minute.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.App
import com.example.ussdaplication.databinding.ItemMinuteBinding
import com.example.ussdaplication.utils.log
import com.example.ussdaplication.utils.setBackColor

class MinuteAdapter(private val context: Context) :
    RecyclerView.Adapter<MinuteAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    fun setList(data: List<String>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: String) -> Unit)? = null

    fun setItemClickListener(f: (id: String) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemMinuteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.linear.setOnClickListener {
                itemClickListener?.invoke("")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMinuteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}