package com.example.ussdaplication.presentation.ui.network.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.App
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.ItemNetworkBinding
import com.example.ussdaplication.domain.model.internet.GetInternetModel

class NetworkAdapter(private val context: Context) :
    RecyclerView.Adapter<NetworkAdapter.ViewHolder>() {

    private val list = mutableListOf<GetInternetModel>()

    fun setList(data: List<GetInternetModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetInternetModel) -> Unit)? = null

    fun setItemClickListener(f: (id: GetInternetModel) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemNetworkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GetInternetModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.txt.text = "Paket " + data.name
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.perM.text = data.duration
            binding.code.text = data.code

            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNetworkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NetworkAdapter.ViewHolder, position: Int) =
        holder.bind(list[position])
}