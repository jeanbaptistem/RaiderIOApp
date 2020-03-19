package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.components.CustomConstraintLayout
import fr.jbme.raiderioapp.data.model.wow.character.Bosses
import fr.jbme.raiderioapp.data.model.wow.character.Raids

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


    fun bind(raid: Raids?) {
        raidNameTextView.text = raid?.name
        raidSummaryTextView.text = "PH"

        raidNMTextView.text =
            raid?.difficulties?.firstOrNull { diff -> diff.difficulty.enum == "NORMAL" }
                ?.bosses?.filter { bosses -> bosses.killCount != 0 }
                ?.size.toString()
        raidHMTextView.text =
            raid?.difficulties?.firstOrNull { diff -> diff.difficulty.enum == "HEROIC" }
                ?.bosses?.filter { bosses -> bosses.killCount != 0 }
                ?.size.toString()
        raidMMTextView.text =
            raid?.difficulties?.firstOrNull { diff -> diff.difficulty.enum == "MYTHIC" }
                ?.bosses?.filter { bosses -> bosses.killCount != 0 }
                ?.size.toString()

        Picasso.get()
            .load(raid?.icon?.url)
            .into(cardViewConstraintLayout)
    }

    fun addBossRecyclerView(bosses: List<Bosses>, raidId: String) {
        bossRecyclerView.run {
            adapter = BossCardViewAdapter(itemView.context, bosses, raidId)
        }
    }
}