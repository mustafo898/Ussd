package com.composer.ussdaplication.presentation.ui.definition.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.ItemDefinitionBinding
import com.composer.ussdaplication.domain.model.tarif.GetTariffModel
import com.composer.ussdaplication.utils.getCurrentLang
import com.composer.ussdaplication.utils.visible

class DefinitionAdapter(private val context: Context) :
    RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {

    private val list = mutableListOf<GetTariffModel>()

    fun setList(data: List<GetTariffModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetTariffModel) -> Unit)? = null

    fun setItemClickListener(f: (id: GetTariffModel) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemDefinitionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetTariffModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)

            binding.txt.text = getCurrentLang(data.name)
            binding.cost.text = context.getString(R.string.money, "${data.price}")
            binding.internet.text =
                context.getString(R.string.megaBayt, getCurrentLang(data.internet))
            binding.minute.text = context.getString(R.string.paket_2, getCurrentLang(data.minute))
            binding.sms.text = context.getString(R.string.paket_1, getCurrentLang(data.sms))

            if (data.internetPrice != 0) {
                binding.withOutLine.visible()
                binding.withOut.text = context.getString(R.string.money, "${data.internetPrice}")
            }
            if (data.minutePrice != 0) {
                binding.withOutMinuteLine.visible()
                binding.withOutMinute.text =
                    context.getString(R.string.money, "${data.minutePrice}")
            }
            if (data.smsPrice != 0) {
                binding.withOutSmsLine.visible()
                binding.withOutSms.text = context.getString(R.string.money, "${data.smsPrice}")
            }

            if (data.description != null) {
                if (data.description.en.isNotEmpty()) {
                    binding.description.visible()
                    binding.description.text = getCurrentLang(data.description)
                }
            }

            if (data.outMinute != null) {
                if (data.outMinute.en.isNotEmpty()) {
                    binding.outMinuteLine.visible()
                    binding.outMinute.text = getCurrentLang(data.outMinute)
                }
            }

            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemDefinitionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}