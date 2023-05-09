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

    private var itemClickListener: ((id: GetInternetModel, Boolean) -> Unit)? = null

    fun setItemClickListener(f: (id: GetInternetModel, Boolean) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemNetworkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GetInternetModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.txt.text = getCurrentLang(data.name)
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.linear2.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.perM.text = getCurrentLang(data.duration)
            binding.code.text = data.turnOn

            if (data.turnOff.isNotEmpty()) {
                binding.disActivate.text = data.turnOff
                binding.disActivateLine.visible()
                binding.activate.visible()
                binding.disActivateBtn.visible()
                binding.activate2.gone()
            } else {
                binding.activate2.visible()
                binding.activate.gone()
                binding.disActivateBtn.gone()
                binding.disActivateLine.gone()
            }

            if (data.description != null) {
                if (data.description._id.isNotEmpty()) {
                    binding.description.text = getCurrentLang(data.description)
                    binding.description.visible()
                } else
                    binding.line2.gone()
            } else
                binding.line2.gone()

            if (data.checkLimit.isNotEmpty()) {
                binding.checkLine.visible()
                binding.checkLimit.text = data.checkLimit
            } else
                binding.checkLine.gone()

            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)

            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data, true)
            }

            binding.linear2.setOnClickListener {
                itemClickListener?.invoke(data, true)
            }

            binding.linear1.setOnClickListener {
                itemClickListener?.invoke(data, false)
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