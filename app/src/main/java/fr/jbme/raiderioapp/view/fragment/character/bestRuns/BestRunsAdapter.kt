package fr.jbme.raiderioapp.view.fragment.character.bestRuns

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterBestRuns

class BestRunsAdapter(
    val context: Context?,
    var bestRunsList: List<CharacterBestRuns.MythicPlusBestRun>
) :
    RecyclerView.Adapter<BestRunsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestRunsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_runs_card_view, parent, false)
        return BestRunsHolder(view)
    }

    override fun getItemCount(): Int {
        return bestRunsList.size
    }

    override fun onBindViewHolder(holder: BestRunsHolder, position: Int) {
        holder.bind(bestRunsList[position])
    }

}
