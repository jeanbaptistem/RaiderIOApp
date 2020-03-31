package fr.jbme.raiderioapp.view.fragment.dungeon.headerRow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks

class HeaderPageAdapter(
    val context: Context,
    var ranksList: List<CharacterRanks.Rank>
) : RecyclerView.Adapter<HeaderPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderPagerViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.dungeon_header_pager_holder, parent, false)
        return HeaderPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: HeaderPagerViewHolder, position: Int) {
        when (position) {
            0 -> holder.bindTab(ranksList.filter { rank -> rank.name!!.contains("class") })
            1 -> holder.bindTab(ranksList.filter { rank -> rank.name!!.contains("spec") })
            2 -> holder.bindTab(ranksList.filter { rank ->
                !rank.name!!.contains("class") &&
                        !rank.name.contains("spec")
            })
        }
    }
}
