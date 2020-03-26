package fr.jbme.raiderioapp.view.fragment.dungeon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.character.CharacterActivityViewModel
import kotlinx.android.synthetic.main.fragment_dungeon.*

class DungeonFragment : Fragment() {
    private val dungeonViewModel: DungeonViewModel by viewModels()

    private lateinit var dungeonRecyclerView: RecyclerView
    private lateinit var dungeonCardViewAdapter: DungeonCardViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dungeon, container, false)
        dungeonCardViewAdapter = DungeonCardViewAdapter(context!!, listOf(), listOf())

        dungeonRecyclerView = root.findViewById(R.id.dungeonRecyclerView)
        dungeonRecyclerView.run {
            adapter = dungeonCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val characterViewModel =
            activity?.let { ViewModelProvider(it).get(CharacterActivityViewModel::class.java) }
        characterViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            dungeonViewModel.selectedCharacter(it)
        })

        observeViewModel(dungeonViewModel)
        return root
    }

    private fun observeViewModel(dungeonViewModel: DungeonViewModel) {
        dungeonViewModel.zippedDungeonData.observe(viewLifecycleOwner, Observer {
            dungeonCardViewAdapter.ranksList = it.first
            dungeonCardViewAdapter.dungeonList = it.second
            dungeonCardViewAdapter.notifyDataSetChanged()
        })
        dungeonViewModel.zippedDungeonDataLoading.observe(viewLifecycleOwner, Observer {
            if (it.first || it.second) {
                dungeonLoading.visibility = View.VISIBLE
            } else if (!it.first && !it.second) {
                dungeonLoading.visibility = View.GONE
            }
        })
    }
}
