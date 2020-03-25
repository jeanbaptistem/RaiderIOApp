package fr.jbme.raiderioapp.view.ui.fragment

import android.os.Bundle
import android.util.Log
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
import fr.jbme.raiderioapp.view.adapter.ArmoryCardViewAdapter
import fr.jbme.raiderioapp.view.model.ArmoryViewModel
import fr.jbme.raiderioapp.view.model.MainActivityViewModel


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
            activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }
        val viewModel: ArmoryViewModel by viewModels()
        activityViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
            viewModel.selectedCharacter(it)
        })

        observeViewModel(viewModel)
        return root
    }

    private fun observeViewModel(viewModel: ArmoryViewModel) {
        Log.i("Armory", "ObserveViewModel")
        viewModel.zippedArmoryLiveData.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.apply {
                itemsData = it
                notifyDataSetChanged()
            }
        })
    }
}
