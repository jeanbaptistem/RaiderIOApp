package fr.jbme.raiderioapp.view.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import fr.jbme.raiderioapp.view.components.RecyclerViewHorizontalDecoration
import fr.jbme.raiderioapp.view.fragment.character.bestRuns.BestRunsAdapter
import fr.jbme.raiderioapp.view.fragment.character.statistics.StatisticsAdapter
import kotlinx.android.synthetic.main.character_info_layout.*
import java.util.*

class CharacterFragment : Fragment() {
    private lateinit var bestRunsAdapter: BestRunsAdapter
    private lateinit var statisticsAdapter: StatisticsAdapter

    private lateinit var buttonFavorites: MaterialButton
    private lateinit var buttonTextFavorites: TextView
    private lateinit var buttonCompare: MaterialButton
    private lateinit var buttonTextCompare: TextView

    private lateinit var bestRunsSeeAll: MaterialButton

    private lateinit var statsButtonWorld: MaterialCardView
    private lateinit var statsButtonWorldText: TextView
    private lateinit var statsButtonRealm: MaterialCardView
    private lateinit var statsButtonRealmText: TextView

    private val worldStats = MutableLiveData(true)
    private var isSearch = false

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.character_fragment, container, false)
        buttonFavorites = root.findViewById(R.id.buttonFavorites)
        buttonTextFavorites = root.findViewById(R.id.buttonTextFavorites)
        bestRunsSeeAll = root.findViewById(R.id.bestRunsSeeAll)
        buttonCompare = root.findViewById(R.id.buttonCompare)
        buttonTextCompare = root.findViewById(R.id.buttonTextCompare)

        statsButtonWorld = root.findViewById(R.id.statsButtonWorld)
        statsButtonWorldText = root.findViewById(R.id.statsButtonWorldText)
        statsButtonRealm = root.findViewById(R.id.statsButtonRealm)
        statsButtonRealmText = root.findViewById(R.id.statsButtonRealmText)

        statsButtonWorld.setOnClickListener { onStatisticsButtonClick(true) }
        statsButtonRealm.setOnClickListener { onStatisticsButtonClick(false) }
        when {
            arguments?.getBundle("self") != null -> {
                isSearch = false
                val selectedCharacterItem = arguments?.getBundle("self")
                    ?.getSerializable("character") as PopupCharacterItem
                characterViewModel.setSelectedCharacter(selectedCharacterItem)
            }
            arguments?.getBundle("search") != null -> {
                isSearch = true
                val selectedCharacterItem = arguments?.getBundle("search")
                    ?.getSerializable("character") as PopupCharacterItem
                characterViewModel.setSelectedCharacter(selectedCharacterItem)
            }
            else -> {
                val mainViewModel =
                    activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }
                mainViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
                    characterViewModel.setSelectedCharacter(it)
                })
            }
        }

        buttonFavorites.isEnabled = isSearch
        buttonCompare.isEnabled = isSearch
        changeButtonState(buttonFavorites, buttonTextFavorites, isSearch)
        changeButtonState(buttonCompare, buttonTextCompare, isSearch)

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

        statisticsAdapter = StatisticsAdapter(context, null, worldStats.value)
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

    private fun changeButtonState(button: MaterialButton, buttonText: TextView, state: Boolean) {
        if (state) {
            button.setIconTintResource(R.color.primaryTextColor)
            buttonText.setTextColor(context!!.getColor(R.color.primaryTextColor))
        } else {
            button.setIconTintResource(R.color.primaryLightColor)
            buttonText.setTextColor(context!!.getColor(R.color.primaryLightColor))
        }

    }

    private fun onStatisticsButtonClick(isWorldButton: Boolean) {
        if (isWorldButton == worldStats.value) return
        worldStats.value = isWorldButton
        val worldText: Int?
        val worldBackground: Int?
        if (worldStats.value!!) {
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
                ranks = it
                notifyDataSetChanged()
            }
        })
        worldStats.observe(viewLifecycleOwner, Observer {
            statisticsAdapter.run {
                worldStats = it
                notifyDataSetChanged()
            }
        })

    }
}