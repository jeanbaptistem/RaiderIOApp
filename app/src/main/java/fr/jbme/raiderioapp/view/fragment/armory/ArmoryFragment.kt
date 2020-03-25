package fr.jbme.raiderioapp.view.fragment.armory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.activity.character.CharacterActivityViewModel


class ArmoryFragment : Fragment() {
    private lateinit var armoryRecyclerView: RecyclerView
    private lateinit var armoryCardViewAdapter: ArmoryCardViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_armory, container, false)
        armoryCardViewAdapter =
            ArmoryCardViewAdapter(
                context,
                Triple(listOf(), listOf(), listOf())
            )

        armoryRecyclerView = root.findViewById(R.id.armoryRecyclerView)
        armoryRecyclerView.run {
            adapter = armoryCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val activityViewModel =
            activity?.let { ViewModelProviders.of(it).get(CharacterActivityViewModel::class.java) }
        val viewModel: ArmoryViewModel by viewModels()
        activityViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            viewModel.selectedCharacter(it)
        })

        observeViewModel(viewModel)
        return root
    }

    private fun observeViewModel(viewModel: ArmoryViewModel) {
        viewModel.zippedArmoryLiveData.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.apply {
                itemsData = it
                notifyDataSetChanged()
            }
        })
    }
}
