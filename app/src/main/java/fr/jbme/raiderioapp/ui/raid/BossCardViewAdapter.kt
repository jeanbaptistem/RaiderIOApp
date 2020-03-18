package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.raidInfo.Encounters

class BossCardViewAdapter(
    val context: Context?,
    private var encounters: List<Encounters>,
    private val raidName: String?
) : RecyclerView.Adapter<BossHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BossHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.raid_boss_item, parent, false)

        return BossHolder(view)
    }

    override fun onBindViewHolder(holder: BossHolder, position: Int) {
        holder.bind(encounters[position], raidName)
    }

    override fun getItemCount(): Int {
        return encounters.size
    }

}