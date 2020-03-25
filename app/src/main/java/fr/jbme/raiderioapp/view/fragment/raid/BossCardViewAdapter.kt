package fr.jbme.raiderioapp.view.fragment.raid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.Encounters

class BossCardViewAdapter(
    val context: Context?,
    var bosses: List<Encounters>,
    var raidName: String
) : RecyclerView.Adapter<BossHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BossHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.raid_boss_item, parent, false)
        return BossHolder(view)
    }

    override fun onBindViewHolder(holder: BossHolder, position: Int) {
        holder.bind(bosses[position], raidName)
    }

    override fun getItemCount(): Int {
        return bosses.size
    }

}