package com.example.ussdaplication.presentation.ui.definition.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.App
import com.example.ussdaplication.databinding.ItemDefinitionBinding
import com.example.ussdaplication.domain.model.tarif.GetTariffModel

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