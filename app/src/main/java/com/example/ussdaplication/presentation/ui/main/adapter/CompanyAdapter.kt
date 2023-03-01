package com.example.ussdaplication.presentation.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ussdaplication.R
import com.example.ussdaplication.databinding.ItemCompanyBinding
import com.example.ussdaplication.model.ItemModel

class CompanyAdapter(private val context: Context) :
    RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {
    private val list = mutableListOf<ItemModel>()

    fun setList(data: List<ItemModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((Int,String) -> Unit)? = null

    fun setItemClickListener(f: (Int,String) -> Unit) {
        itemClickListener = f
    }

    private var selected = -1

    inner class ViewHolder(private val binding: ItemCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemModel) {

            if (data.isSelected) {
                selected = layoutPosition
                binding.card.setCardBackgroundColor(data.color)
                binding.card.strokeColor = data.color

            } else {
                binding.card.setCardBackgroundColor(context.getColor(R.color.white))
                binding.card.strokeColor = data.color
            }

            itemView.setOnClickListener {
                list[selected].isSelected = false
                notifyItemChanged(selected)
                selected = layoutPosition
                list[layoutPosition].isSelected = true
                notifyItemChanged(selected)
                itemClickListener?.invoke(data.color,data.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}