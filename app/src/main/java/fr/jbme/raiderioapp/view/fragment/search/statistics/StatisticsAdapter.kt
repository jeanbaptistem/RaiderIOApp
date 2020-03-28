package fr.jbme.raiderioapp.view.fragment.search.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R

class StatisticsAdapter(val context: Context?, var staticsList: List<Any>) :
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
