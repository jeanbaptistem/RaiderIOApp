package fr.jbme.raiderioapp.view.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.fragment.search.bestRuns.BestRunsAdapter
import fr.jbme.raiderioapp.view.fragment.search.statistics.StatisticsAdapter

class SearchFragment : Fragment() {

    private lateinit var bestRunsRecyclerView: RecyclerView
    private lateinit var bestRunsAdapter: BestRunsAdapter

    private lateinit var statisticsRecyclerView: RecyclerView
    private lateinit var statisticsAdapter: StatisticsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        bestRunsRecyclerView = root.findViewById(R.id.bestRunsRecyclerView)
        bestRunsAdapter =
            BestRunsAdapter(
                context,
                listOf<Any>(1, 2, 3, 4, 5, 6)
            )
        bestRunsRecyclerView.adapter = bestRunsAdapter

        statisticsRecyclerView = root.findViewById(R.id.statisticsRecyclerView)
        statisticsAdapter =
            StatisticsAdapter(
                context,
                listOf<Any>(1, 2, 3, 4, 5, 6)
            )
        statisticsRecyclerView.adapter = statisticsAdapter

        return root
    }
}