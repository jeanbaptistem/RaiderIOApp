package fr.jbme.raiderioapp.view.fragment.character.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.Rank

class StatisticsAdapter(val context: Context?, var staticsList: List<Rank>) :
    RecyclerView.Adapter<StatisticsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.statistics_card_view, parent, false)
        return StatisticsHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return staticsList.size
    }

    override fun onBindViewHolder(holder: StatisticsHolder, position: Int) {}

}
