package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.components.DynamicHeightImageView
import fr.jbme.raiderioapp.utils.Whatever.repeat

@SuppressLint("SetTextI18n")
class RaidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dynamicImageView: DynamicHeightImageView =
        itemView.findViewById(R.id.dynamicImageView)

    private val raidNameTextView: TextView = itemView.findViewById(R.id.raidNameTextView)

    private val raidLFRHeaderTextView: TextView = itemView.findViewById(R.id.raidLFRHeader)
    private val raidNMHeaderTextView: TextView = itemView.findViewById(R.id.raidNMHeader)
    private val raidHMHeaderTextView: TextView = itemView.findViewById(R.id.raidHMHeader)
    private val raidMMHeaderTextView: TextView = itemView.findViewById(R.id.raidMMHeader)


    private val raidLFRTextView: TextView = itemView.findViewById(R.id.raidLFRTextView)
    private val raidNMTextView: TextView = itemView.findViewById(R.id.raidNMTextView)
    private val raidHMTextView: TextView = itemView.findViewById(R.id.raidHMTextView)
    private val raidMMTextView: TextView = itemView.findViewById(R.id.raidMMTextView)

    private val raidToggleBossListButton: Button =
        itemView.findViewById(R.id.raidToggleBossListButton)

    private val bossRecyclerView: RecyclerView =
        itemView.findViewById(R.id.raidProgressionBossRecyclerView)
    private val bossCardViewAdapter: BossCardViewAdapter =
        BossCardViewAdapter(itemView.context, "")//, listOf(), "")

    private val difficultyListIterator =
        sequenceOf("MYTHIC", "HEROIC", "NORMAL", "LFR").repeat().iterator()

/*
    fun bind(raid: Raids?) {
        bossRecyclerView.adapter = bossCardViewAdapter

        raidNameTextView.text = raid?.name

        raidToggleBossListButton.setOnClickListener {
            val difficulty = difficultyListIterator.next()
            bossCardViewAdapter.run {
                bosses =
                    raid?.difficulties?.firstOrNull { difficulties -> difficulties.difficulty.enum == difficulty }?.bosses
                        ?: listOf()
                raidId = raid?.id ?: ""
                notifyDataSetChanged()
            }

            raidToggleBossListButton.text =
                displayBossCounter(raid, difficulty) + when (difficulty) {
                    "MYTHIC" -> " MM"
                    "HEROIC" -> " HC"
                    "NORMAL" -> " NM"
                    "LFR" -> " LFR"
                    else -> "%%"
                }
            raidToggleBossListButton.setBackgroundColor(
                when (difficulty) {
                    "MYTHIC" -> itemView.context.getColor(R.color.itemQualityLegendary)
                    "HEROIC" -> itemView.context.getColor(R.color.itemQualityEpic)
                    "NORMAL" -> itemView.context.getColor(R.color.itemQualityRare)
                    "LFR" -> itemView.context.getColor(R.color.itemQualityUncommon)
                    else -> itemView.context.getColor(R.color.itemQualityPoor)
                }
            )

        }
        raidToggleBossListButton.callOnClick()

        when (itemView.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                raidLFRHeaderTextView.text = itemView.context.getString(R.string.lfr_long)
                raidNMHeaderTextView.text = itemView.context.getString(R.string.normal_long)
                raidHMHeaderTextView.text = itemView.context.getString(R.string.heroic_long)
                raidMMHeaderTextView.text = itemView.context.getString(R.string.mythic_long)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                raidLFRHeaderTextView.text = itemView.context.getString(R.string.lfr_short)
                raidNMHeaderTextView.text = itemView.context.getString(R.string.normal_short)
                raidHMHeaderTextView.text = itemView.context.getString(R.string.heroic_short)
                raidMMHeaderTextView.text = itemView.context.getString(R.string.mythic_short)
            }
        }

        raidLFRTextView.text = displayBossCounter(raid, "LFR")
        raidNMTextView.text = displayBossCounter(raid, "NORMAL")
        raidHMTextView.text = displayBossCounter(raid, "HEROIC")
        raidMMTextView.text = displayBossCounter(raid, "MYTHIC")

        Picasso.get()
            .load(raid?.icon?.url)
            .into(dynamicImageView)
    }*/
/*
    private fun displayBossCounter(raid: Raids?, difficulty: String?): String? {
        val difficulties =
            raid?.difficulties?.firstOrNull { diff -> diff.difficulty.enum == difficulty }
        return difficulties?.bosses?.filter { bosses -> bosses.killCount != 0 }?.size.toString() + "/" +
                difficulties?.total.toString()
    }*/
}