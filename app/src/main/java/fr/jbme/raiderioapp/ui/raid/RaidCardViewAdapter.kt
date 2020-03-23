package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.blizzard.raidInfo.Instances

class RaidCardViewAdapter(
    val context: Context?,
    var instancesList: List<Instances>
) : RecyclerView.Adapter<RaidHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaidHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.raid_cardview_row, parent, false)
        return RaidHolder(view)
    }

    override fun onBindViewHolder(holder: RaidHolder, position: Int) {
        holder.bind(instancesList[position])
    }

    override fun getItemCount(): Int {
        return instancesList.size
    }

}