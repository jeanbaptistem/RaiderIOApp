package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.components.CustomConstraintLayout
import fr.jbme.raiderioapp.data.BG_RAID_URL
import fr.jbme.raiderioapp.data.model.character.Raid
import fr.jbme.raiderioapp.data.model.raidInfo.Encounters
import fr.jbme.raiderioapp.utils.SlugParser

@SuppressLint("SetTextI18n")
class RaidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cardViewConstraintLayout: CustomConstraintLayout =
        itemView.findViewById(R.id.raidCardViewLayout)

    private val raidNameTextView: TextView = itemView.findViewById(R.id.raidNameTextView)

    private val raidNMTextView: TextView = itemView.findViewById(R.id.raidNMTextView)
    private val raidHMTextView: TextView = itemView.findViewById(R.id.raidHMTextView)
    private val raidMMTextView: TextView = itemView.findViewById(R.id.raidMMTextView)

    private val raidSummaryCardView: CardView = itemView.findViewById(R.id.raidSummaryCardView)
    private val raidSummaryTextView: TextView = itemView.findViewById(R.id.raidSummaryTextView)

    private val bossRecyclerView: RecyclerView =
        itemView.findViewById(R.id.raidProgressionBossRecyclerView)


    fun bind(raid: Raid) {
        raidNameTextView.text = raid.name
        raidSummaryTextView.text = raid.summary
        when {
            raid.summary?.contains("M")!! -> raidSummaryCardView.setCardBackgroundColor(
                itemView.resources.getColor(
                    R.color.itemQualityLegendary
                )
            )
            raid.summary?.contains("H")!! -> raidSummaryCardView.setCardBackgroundColor(
                itemView.resources.getColor(
                    R.color.itemQualityEpic
                )
            )
            raid.summary?.contains("N")!! -> raidSummaryCardView.setCardBackgroundColor(
                itemView.resources.getColor(
                    R.color.itemQualityRare
                )
            )
            else -> raidSummaryCardView.visibility = View.INVISIBLE
        }
        raidNMTextView.text = raid.normalBossesKilled.toString() + "/" + raid.totalBosses.toString()
        raidHMTextView.text = raid.heroicBossesKilled.toString() + "/" + raid.totalBosses.toString()
        raidMMTextView.text = raid.mythicBossesKilled.toString() + "/" + raid.totalBosses.toString()
    }

    fun setBackground(raid: Raid?) {
        val bgUrl = BG_RAID_URL.format(SlugParser.parseToSlug(raid?.name))
        Picasso.get()
            .load(bgUrl)
            .into(cardViewConstraintLayout)
    }

    fun addBossRecyclerView(encounters: List<Encounters>?, raidName: String?) {
        bossRecyclerView.run {
            adapter = BossCardViewAdapter(itemView.context, encounters ?: listOf(), raidName)
        }
    }
}