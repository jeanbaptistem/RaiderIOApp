package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.wow.character.Raids

class RaidCardViewAdapter(
    val context: Context?,
    var raidList: List<Raids>
) : RecyclerView.Adapter<RaidHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaidHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.raid_cardview_row, parent, false)
        return RaidHolder(view)
    }

    override fun onBindViewHolder(holder: RaidHolder, position: Int) {
        val raid = raidList[position]
        Log.i("raid", raid.toString())

        holder.bind(raid)
        val bosses =
            raid.difficulties.firstOrNull { difficulties -> difficulties.difficulty.enum == "MYTHIC" }?.bosses
        holder.addBossRecyclerView(bosses ?: listOf(), raid.id)
    }

    override fun getItemCount(): Int {
        return raidList.size
    }

}