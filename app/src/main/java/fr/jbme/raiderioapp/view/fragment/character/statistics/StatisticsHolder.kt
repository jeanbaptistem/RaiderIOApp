package fr.jbme.raiderioapp.view.fragment.character.statistics

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks

class StatisticsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val statisticsMainTitle: TextView =
        itemView.findViewById(R.id.statisticsMainTitle)
    private val statisticsMainScore: TextView =
        itemView.findViewById(R.id.statisticsMainScore)
    private val statisticsSecondaryTitle: TextView =
        itemView.findViewById(R.id.statisticsSecondaryTitle)
    private val statisticsSecondaryScore: TextView =
        itemView.findViewById(R.id.statisticsSecondaryScore)

    @SuppressLint("DefaultLocale")
    fun bind(realmStats: Boolean, rank: CharacterRanks.Rank?, rankFaction: CharacterRanks.Rank?) {
        statisticsMainTitle.text = rank?.name?.capitalize()
        statisticsMainScore.text =
            if (!realmStats) rank?.world.toString() else rank?.realm.toString()
        statisticsSecondaryTitle.text = rankFaction?.name?.capitalize()
        statisticsSecondaryScore.text =
            if (!realmStats) rankFaction?.world.toString() else rankFaction?.realm.toString()
    }
}
