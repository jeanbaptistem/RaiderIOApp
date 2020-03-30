package fr.jbme.raiderioapp.view.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import fr.jbme.raiderioapp.view.fragment.character.bestRuns.BestRunsAdapter
import fr.jbme.raiderioapp.view.fragment.character.statistics.StatisticsAdapter
import kotlinx.android.synthetic.main.character_info_layout.*
import java.util.*

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

        val mainViewModel =
            activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }
        mainViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            characterViewModel.setSelectedCharacter(it)
        })

        bestRunsRecyclerView = root.findViewById(R.id.bestRunsRecyclerView)
        bestRunsAdapter =
            BestRunsAdapter(
                context,
                listOf()
            )
        bestRunsRecyclerView.adapter = bestRunsAdapter

        statisticsRecyclerView = root.findViewById(R.id.statisticsRecyclerView)
        statisticsAdapter =
            StatisticsAdapter(
                context,
                listOf()
            )
        statisticsRecyclerView.adapter = statisticsAdapter

        observeViewModel(characterViewModel)
        return root
    }

    private fun observeViewModel(characterViewModel: CharacterViewModel) {
        characterViewModel.characterScore.observe(viewLifecycleOwner, Observer {
            Picasso.get()
                .load(characterViewModel.selectedCharacter.value?.thumbnailUrl)
                .placeholder(R.color.primaryColor)
                .error(R.color.errorColor)
                .into(charThumbnail)
            charClass.text = it._class
            charName.text = it.name
            region.text = "(${it.region.toUpperCase(Locale.ROOT)})"
            realm.text = it.realm
            bestScoreTextView.text = it.mythic_plus_scores_by_season
                .first().scores.all.toString()
        })
        characterViewModel.characterBestRuns.observe(viewLifecycleOwner, Observer {
            bestRunsAdapter.run {
                bestRunsList = it.sortedByDescending { it.mythic_level }
                notifyDataSetChanged()
            }
        })
        characterViewModel.characterRanks.observe(viewLifecycleOwner, Observer {
            statisticsAdapter.run {
                staticsList = it
                notifyDataSetChanged()
            }
        })

    }
}