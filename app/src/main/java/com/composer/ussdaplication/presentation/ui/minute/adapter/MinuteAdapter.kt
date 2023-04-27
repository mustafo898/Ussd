package com.composer.ussdaplication.presentation.ui.minute.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.ItemMinuteBinding
import com.composer.ussdaplication.domain.model.minute.GetMinuteModel
import com.composer.ussdaplication.utils.getCurrentLang
import com.composer.ussdaplication.utils.gone
import com.composer.ussdaplication.utils.visible

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
            binding.txt.setTextColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)

            binding.txt.text = context.getString(R.string.paket_2, getCurrentLang(data.name))
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.perM.text = context.getString(R.string.duration, getCurrentLang(data.duration))
            binding.code.text = context.getString(R.string.code, data.turnOn)
            binding.disActivate.text = context.getString(R.string.disActivateCode, data.turnOff)

            if (data.turnOff.isNotEmpty()) {
                binding.disActivate.text = context.getString(R.string.disActivateCode, data.turnOff)
                binding.disActivate.visible()
            } else
                binding.disActivate.gone()

            if (data.description != null) {
                if (data.description._id.isNotEmpty()) {
                    binding.description.text = getCurrentLang(data.description)
                    binding.description.visible()
                } else
                    binding.line2.gone()
            } else
                binding.line2.gone()

            if (data.checkLimit.isNotEmpty()) {
                binding.checkLimit.visible()
                binding.checkLimit.text = context.getString(R.string.checkLimit, data.checkLimit)
            } else
                binding.checkLimit.gone()

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