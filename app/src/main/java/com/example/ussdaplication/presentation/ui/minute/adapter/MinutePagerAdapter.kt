package com.example.ussdaplication.presentation.ui.minute.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ussdaplication.presentation.ui.minute.MinutePagerFragment

class MinutePagerAdapter(
    fa: FragmentManager,
    lifecycle: Lifecycle,
    private val list: List<String>
) : FragmentStateAdapter(fa, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return MinutePagerFragment()
    }

}