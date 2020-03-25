package fr.jbme.raiderioapp.view.fragment.raid

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.BG_RAID_URL
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.Instances
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.utils.Whatever.repeat
import fr.jbme.raiderioapp.view.components.DynamicHeightImageView

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
        BossCardViewAdapter(
            itemView.context,
            listOf(),
            ""
        )

    fun bind(instances: Instances?) {
        val difficultyListIterator =
            sequenceOf("MYTHIC", "HEROIC", "NORMAL", "LFR").repeat().iterator()
        bossRecyclerView.adapter = bossCardViewAdapter

        raidNameTextView.text = instances?.instance?.name

        raidToggleBossListButton.setOnClickListener {
            val difficulty = difficultyListIterator.next()
            bossCardViewAdapter.run {
                bosses =
                    instances?.modes?.firstOrNull { modes -> modes.difficulty.type == difficulty }?.progress?.encounters
                        ?: listOf()
                raidName = instances?.instance?.name.toString()
                notifyDataSetChanged()
            }

            raidToggleBossListButton.text =
                displayBossCounter(instances, difficulty) + when (difficulty) {
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

        raidLFRTextView.text = displayBossCounter(instances, "LFR")
        raidNMTextView.text = displayBossCounter(instances, "NORMAL")
        raidHMTextView.text = displayBossCounter(instances, "HEROIC")
        raidMMTextView.text = displayBossCounter(instances, "MYTHIC")

        Picasso.get().load(BG_RAID_URL.format(Whatever.parseToSlug(instances?.instance?.name)))
            .into(dynamicImageView)
    }

    private fun displayBossCounter(instances: Instances?, difficulty: String?): String {
        val difficulties =
            instances?.modes?.firstOrNull { modes -> modes.difficulty.type == difficulty }
        return "${difficulties?.progress?.completed_count ?: 0}/${difficulties?.progress?.total_count ?: 0}"

    }
}