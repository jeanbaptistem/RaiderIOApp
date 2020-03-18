package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.character.Raid
import fr.jbme.raiderioapp.data.model.raidInfo.Instances

class RaidCardViewAdapter(
    val context: Context?,
    var raidInstancesList: Pair<List<Raid>, List<Instances>>
) : RecyclerView.Adapter<RaidHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaidHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.raid_cardview_row, parent, false)
        return RaidHolder(view)
    }

    override fun onBindViewHolder(holder: RaidHolder, position: Int) {
        val raid = raidInstancesList.first[position]
        val instancesList = raidInstancesList.second


        holder.bind(raid)
        holder.setBackground(raid)

        val encounters =
            instancesList.first { instances -> instances.instance.name == raid.name }
                .modes.firstOrNull { modes -> modes.difficulty.type == "MYTHIC" }
                ?.progress?.encounters
        holder.addBossRecyclerView(encounters, raid.name)
    }

    override fun getItemCount(): Int {
        return raidInstancesList.first.size
    }

}