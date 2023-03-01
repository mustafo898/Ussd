package com.example.ussdaplication.presentation.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ussdaplication.databinding.ItemViewPagerBinding
import com.example.ussdaplication.domain.ads.GetNewsModel
import com.example.ussdaplication.utils.Constants

class ViewPagerAdapter(private val context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    private val list = mutableListOf<GetNewsModel>()

    fun setList(data: List<GetNewsModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    private var selected = -1

    inner class ViewHolder(private val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetNewsModel) {
            Glide.with(binding.image.context)
                .load(Constants.BASE_URL + "/public/uploads/" + data.image)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}