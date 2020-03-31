package fr.jbme.raiderioapp.view.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import fr.jbme.raiderioapp.view.components.RecyclerViewHorizontalDecoration
import fr.jbme.raiderioapp.view.fragment.character.bestRuns.BestRunsAdapter
import fr.jbme.raiderioapp.view.fragment.character.statistics.StatisticsAdapter
import kotlinx.android.synthetic.main.character_info_layout.*
import java.util.*

class CharacterFragment : Fragment() {
    private lateinit var bestRunsAdapter: BestRunsAdapter
    private lateinit var statisticsAdapter: StatisticsAdapter

    private lateinit var statsButtonWorld: MaterialCardView
    private lateinit var statsButtonWorldText: TextView
    private lateinit var statsButtonRealm: MaterialCardView
    private lateinit var statsButtonRealmText: TextView
    private var worldStats = true

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.character_fragment, container, false)
        statsButtonWorld = root.findViewById(R.id.statsButtonWorld)
        statsButtonWorldText = root.findViewById(R.id.statsButtonWorldText)
        statsButtonRealm = root.findViewById(R.id.statsButtonRealm)
        statsButtonRealmText = root.findViewById(R.id.statsButtonRealmText)

        statsButtonWorld.setOnClickListener { onStatisticsButtonClick(true) }
        statsButtonRealm.setOnClickListener { onStatisticsButtonClick(false) }

        val mainViewModel =
            activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }
        mainViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            characterViewModel.setSelectedCharacter(it)
        })

        val recyclerViewHorizontalDecoration = RecyclerViewHorizontalDecoration(
            context?.resources?.getDimensionPixelSize(R.dimen.card_view_margin_horizontal),
            context?.resources?.getDimensionPixelSize(R.dimen.card_view_margin_vertical),
            context?.resources?.getDimensionPixelSize(R.dimen.card_view_margin_horizontal),
            context?.resources?.getDimensionPixelSize(R.dimen.card_view_margin_vertical),
            1
        )

        bestRunsAdapter = BestRunsAdapter(context, listOf())
        val bestRunsRecyclerView: RecyclerView = root.findViewById(R.id.bestRunsRecyclerView)
        bestRunsRecyclerView.run {
            adapter = bestRunsAdapter
            layoutManager = LinearLayoutManager(context)
                .apply { orientation = RecyclerView.HORIZONTAL }
            addItemDecoration(recyclerViewHorizontalDecoration)
        }

        statisticsAdapter = StatisticsAdapter(context, listOf())
        val statisticsRecyclerView: RecyclerView = root.findViewById(R.id.statisticsRecyclerView)
        statisticsRecyclerView.run {
            adapter = statisticsAdapter
            layoutManager = LinearLayoutManager(context)
                .apply { orientation = RecyclerView.HORIZONTAL }
            addItemDecoration(recyclerViewHorizontalDecoration)
        }

        observeViewModel(characterViewModel)

        return root
    }

    private fun onStatisticsButtonClick(isWorldButton: Boolean) {
        if (isWorldButton == worldStats) return
        worldStats = !worldStats
        val worldText: Int?
        val worldBackground: Int?
        if (worldStats) {
            worldText = context?.getColor(R.color.primaryTextColor)
            worldBackground = context?.getColor(R.color.primaryColor)
        } else {
            worldText = context?.getColor(R.color.primaryColor)
            worldBackground = context?.getColor(R.color.primaryTextColor)
        }
        statsButtonWorld.setCardBackgroundColor(worldBackground!!)
        statsButtonWorldText.setTextColor(worldText!!)
        statsButtonRealm.setCardBackgroundColor(worldText)
        statsButtonRealmText.setTextColor(worldBackground)
    }

    private fun observeViewModel(characterViewModel: CharacterViewModel) {
        characterViewModel.characterScore.observe(viewLifecycleOwner, Observer {
            Picasso.get()
                .load(characterViewModel.selectedCharacter.value?.thumbnailUrl)
                .placeholder(R.color.primaryColor)
                .error(R.color.errorColor)
                .into(charThumbnail)
            charClass.text = it.classX
            charName.text = it.name
            region.text = "(${it.region?.toUpperCase(Locale.ROOT)})"
            realm.text = it.realm
            bestScoreTextView.text = it.mythicPlusScoresBySeason?.first()?.scores?.all.toString()
        })
        characterViewModel.characterBestRuns.observe(viewLifecycleOwner, Observer {
            bestRunsAdapter.run {
                bestRunsList = it.sortedByDescending { it.mythicLevel }
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