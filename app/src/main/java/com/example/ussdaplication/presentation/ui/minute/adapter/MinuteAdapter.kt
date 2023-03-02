package com.example.ussdaplication.presentation.ui.minute.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.App
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.ItemMinuteBinding
import com.example.ussdaplication.domain.model.minute.GetMinuteModel

class MinuteAdapter(private val context: Context) :
    RecyclerView.Adapter<MinuteAdapter.ViewHolder>() {

    private val list = mutableListOf<GetMinuteModel>()

    fun setList(data: List<GetMinuteModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetMinuteModel) -> Unit)? = null

    fun setItemClickListener(f: (id: GetMinuteModel) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemMinuteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GetMinuteModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)

            binding.txt.text = context.getString(R.string.paket, data.company)
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.code.text = context.getString(R.string.code, data.code)

            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data)
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