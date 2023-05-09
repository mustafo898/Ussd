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

    private var itemClickListener: ((id: GetMinuteModel, Boolean) -> Unit)? = null

    fun setItemClickListener(f: (id: GetMinuteModel, Boolean) -> Unit) {

        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemMinuteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GetMinuteModel) {
            binding.txt.setTextColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)
            binding.linear1.setBackgroundColor(com.composer.ussdaplication.App.sharedPreference.operatorColor)

            binding.txt.text = context.getString(R.string.paket_2, getCurrentLang(data.name))
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.perM.text = getCurrentLang(data.duration)
            binding.code.text = data.turnOn
            binding.disActivate.text = data.turnOff

            if (data.turnOff.isNotEmpty()) {
                binding.disActivate.text = data.turnOff
                binding.disActivateLine.visible()
                binding.disActivateBtn.visible()
                binding.activate2.gone()
                binding.activate.visible()
            } else {
                binding.disActivateLine.gone()
                binding.disActivateBtn.gone()
                binding.activate2.visible()
                binding.activate.gone()
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
        ItemMinuteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}