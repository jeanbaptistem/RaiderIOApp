package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.components.DynamicHeightImageView
import fr.jbme.raiderioapp.data.model.wow.character.Bosses
import fr.jbme.raiderioapp.data.model.wow.character.Raids

@SuppressLint("SetTextI18n")
class RaidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dynamicImageView: DynamicHeightImageView =
        itemView.findViewById(R.id.dynamicImageView)

    private val raidNameTextView: TextView = itemView.findViewById(R.id.raidNameTextView)

    private val raidLFRTextView: TextView = itemView.findViewById(R.id.raidLFRTextView)
    private val raidNMTextView: TextView = itemView.findViewById(R.id.raidNMTextView)
    private val raidHMTextView: TextView = itemView.findViewById(R.id.raidHMTextView)
    private val raidMMTextView: TextView = itemView.findViewById(R.id.raidMMTextView)

    private val raidSummaryCardView: CardView = itemView.findViewById(R.id.raidSummaryCardView)
    private val raidSummaryTextView: TextView = itemView.findViewById(R.id.raidSummaryTextView)

    private val bossRecyclerView: RecyclerView =
        itemView.findViewById(R.id.raidProgressionBossRecyclerView)


    fun bind(raid: Raids?) {
        raidNameTextView.text = raid?.name
        raidSummaryTextView.text = "PH"

        raidLFRTextView.text = displayBossCounter(raid, "LFR")
        raidNMTextView.text = displayBossCounter(raid, "NORMAL")
        raidHMTextView.text = displayBossCounter(raid, "HEROIC")
        raidMMTextView.text = displayBossCounter(raid, "MYTHIC")

        Picasso.get()
            .load(raid?.icon?.url)
            .into(dynamicImageView)
    }

    private fun displayBossCounter(raid: Raids?, difficulty: String?): String? {
        val difficulties =
            raid?.difficulties?.firstOrNull { diff -> diff.difficulty.enum == difficulty }
        return difficulties?.bosses?.filter { bosses -> bosses.killCount != 0 }?.size.toString() + "/" +
                difficulties?.total.toString()
    }

    fun addBossRecyclerView(bosses: List<Bosses>, raidId: String) {
        bossRecyclerView.run {
            adapter = BossCardViewAdapter(itemView.context, bosses, raidId)
        }
    }
}