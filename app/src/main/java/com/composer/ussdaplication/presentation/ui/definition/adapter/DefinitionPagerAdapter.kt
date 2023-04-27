package com.composer.ussdaplication.presentation.ui.definition.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.composer.ussdaplication.presentation.ui.definition.DefinitionFragment
import com.composer.ussdaplication.presentation.ui.network.pager.NetworkPagerFragment

class DefinitionPagerAdapter(
    fa: FragmentManager, lifecycle: Lifecycle, private val list: List<String>
) : FragmentStateAdapter(fa, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }


    override fun createFragment(position: Int): Fragment {
        return DefinitionFragment.newInstance(list[position])
    }
}