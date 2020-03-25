package fr.jbme.raiderioapp.view.fragment.raid

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.character.CharacterActivityViewModel

class RaidFragment : Fragment() {
    private val raidViewModel1: RaidViewModel by viewModels()

    private lateinit var raidRecyclerView: RecyclerView

    private lateinit var raidCardViewAdapter: RaidCardViewAdapter

    private lateinit var portraitLayoutManager: LinearLayoutManager
    private lateinit var landscapeLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_raid, container, false)
        raidCardViewAdapter =
            RaidCardViewAdapter(
                context,
                listOf()
            )

        portraitLayoutManager = LinearLayoutManager(context)
        landscapeLayoutManager = GridLayoutManager(context, 2)

        raidRecyclerView = root.findViewById(R.id.raidRecyclerView)
        raidRecyclerView.run {
            layoutManager = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> landscapeLayoutManager
                Configuration.ORIENTATION_PORTRAIT -> portraitLayoutManager
                else -> portraitLayoutManager
            }
            adapter = raidCardViewAdapter
        }

        val characterViewModel =
            activity?.let { ViewModelProvider(it).get(CharacterActivityViewModel::class.java) }
        characterViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            raidViewModel1.selectedCharacter(it)
        })
        observeViewModel(raidViewModel1)
        return root
    }

    private fun observeViewModel(raidViewModel: RaidViewModel) {
        raidViewModel.characterRaidInfo.observe(viewLifecycleOwner, Observer {
            raidCardViewAdapter.instancesList = it
            raidCardViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        raidRecyclerView.run {
            layoutManager = when (newConfig.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> landscapeLayoutManager
                Configuration.ORIENTATION_PORTRAIT -> portraitLayoutManager
                else -> portraitLayoutManager
            }
            adapter = raidCardViewAdapter
        }
    }
}
