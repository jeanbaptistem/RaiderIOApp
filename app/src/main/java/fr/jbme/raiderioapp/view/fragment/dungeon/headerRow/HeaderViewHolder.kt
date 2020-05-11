package fr.jbme.raiderioapp.view.fragment.dungeon.headerRow

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks


class HeaderViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tabLayout: TabLayout = itemView.findViewById(R.id.dungeonHeaderTabsLayout)
    private val viewPager: ViewPager2 = itemView.findViewById(R.id.dungeonHeaderViewPager)
    private val adapter: HeaderPageAdapter = HeaderPageAdapter(context, listOf())

    init {
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager,
            TabConfigurationStrategy { tab, position ->
                tab.text = context.resources.getStringArray(R.array.dungeon_ranks_title)[position]
            }).attach()
    }

    fun bind(ranksList: List<CharacterRanks.Rank>) {
        adapter.run {
            this.ranksList = ranksList
            notifyDataSetChanged()
        }
    }

}