package com.composer.ussdaplication.presentation.ui.network.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.ItemNetworkBinding
import com.composer.ussdaplication.domain.model.internet.GetInternetModel
import com.composer.ussdaplication.utils.getCurrentLang
import com.composer.ussdaplication.utils.gone
import com.composer.ussdaplication.utils.visible

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
            binding.txt.text = getCurrentLang(data.name)
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.perM.text = context.getString(R.string.duration, getCurrentLang(data.duration))
            binding.code.text = context.getString(R.string.code, data.turnOn)

            if (data.turnOff.isNotEmpty()) {
                binding.disActivate.text = context.getString(R.string.disActivateCode, data.turnOff)
                binding.disActivate.visible()
            } else
                binding.disActivate.gone()

            if (data.description != null) {
                if (data.description._id.isNotEmpty()) {
                    binding.description.text = getCurrentLang(data.description)
                    binding.description.visible()
                }else
                    binding.line2.gone()
            } else
                binding.line2.gone()

            if (data.checkLimit.isNotEmpty()) {
                binding.checkLimit.visible()
                binding.checkLimit.text = context.getString(R.string.checkLimit, data.checkLimit)
            } else
                binding.checkLimit.gone()

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