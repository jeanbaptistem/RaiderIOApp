package fr.jbme.raiderioapp.view.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.fragment.character.bestRuns.BestRunsAdapter
import fr.jbme.raiderioapp.view.fragment.character.statistics.StatisticsAdapter

class CharacterFragment : Fragment() {

    private lateinit var bestRunsRecyclerView: RecyclerView
    private lateinit var bestRunsAdapter: BestRunsAdapter

    private lateinit var statisticsRecyclerView: RecyclerView
    private lateinit var statisticsAdapter: StatisticsAdapter

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.character_fragment, container, false)

        val charBundle = arguments?.getBundle("BUNDLE_CHARACTER")

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

        observeViewModel(characterViewModel)
        return root
    }

    private fun observeViewModel(characterViewModel: CharacterViewModel) {


    }
}