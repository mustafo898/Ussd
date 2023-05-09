package com.composer.ussdaplication.presentation.ui.ussd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.composer.ussdaplication.App
import com.composer.ussdaplication.R
import com.composer.ussdaplication.databinding.ItemUssdBinding
import com.composer.ussdaplication.domain.model.ussd.GetUssdModel
import com.composer.ussdaplication.utils.getCurrentLang

class UssdAdapter(private val context: Context) :
    RecyclerView.Adapter<UssdAdapter.ViewHolder>() {

    private val list = mutableListOf<GetUssdModel>()

    fun setList(data: List<GetUssdModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: GetUssdModel) -> Unit)? = null

    fun setItemClickListener(f: (id: GetUssdModel) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemUssdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetUssdModel) {
            binding.txt.setTextColor(App.sharedPreference.operatorColor)
            binding.linear.setBackgroundColor(App.sharedPreference.operatorColor)

            binding.txt.text = getCurrentLang(data.name)
            binding.code.text = data.code
            binding.description.text = getCurrentLang(data.description)

            binding.linear.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUssdBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}