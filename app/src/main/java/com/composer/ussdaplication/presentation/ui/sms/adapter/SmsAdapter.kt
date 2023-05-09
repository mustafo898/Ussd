package com.composer.ussdaplication.presentation.ui.sms.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.ItemSmsBinding
import com.composer.ussdaplication.domain.model.sms.GetSmsModel
import com.composer.ussdaplication.utils.getCurrentLang
import com.composer.ussdaplication.utils.gone
import com.composer.ussdaplication.utils.visible

class SmsAdapter(private val context: Context) :
    RecyclerView.Adapter<SmsAdapter.ViewHolder>() {

    private val list = mutableListOf<GetSmsModel>()

    fun setList(data: List<GetSmsModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetSmsModel, Boolean) -> Unit)? = null

    fun setItemClickListener(f: (id: GetSmsModel, Boolean) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemSmsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetSmsModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)
            binding.linear2.setBackgroundColor(App.sharedPreference.operatorColor)

            binding.txt.text = context.getString(R.string.paket_1, getCurrentLang(data.name))
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.perM.text = getCurrentLang(data.duration)
            binding.code.text = data.turnOn

            if (data.turnOff.isNotEmpty()) {
                binding.disActivate.text = data.turnOff
                binding.disActivateLine.visible()
                binding.activate.visible()
                binding.disActivateBtn.visible()
                binding.activate2.gone()
            } else {
                binding.disActivateLine.gone()
                binding.activate.gone()
                binding.disActivateBtn.gone()
                binding.activate2.visible()
            }

            if (data.checkLimit.isNotEmpty()) {
                binding.checkLine.visible()
                binding.checkLimit.text = data.checkLimit
            } else
                binding.checkLine.gone()

            if (data.description != null) {
                if (data.description._id.isNotEmpty()) {
                    binding.description.text = getCurrentLang(data.description)
                    binding.description.visible()
                } else
                    binding.line2.gone()
            } else
                binding.line2.gone()

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
        ItemSmsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}