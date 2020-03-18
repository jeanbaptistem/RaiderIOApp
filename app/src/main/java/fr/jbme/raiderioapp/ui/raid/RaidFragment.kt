package fr.jbme.raiderioapp.ui.raid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.model.character.Raid
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.data.model.raidInfo.Instances
import fr.jbme.raiderioapp.network.utils.LiveDataUtils
import java.util.*

class RaidFragment : Fragment() {

    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private lateinit var raidViewModel: RaidViewModel

    private lateinit var raidRecyclerView: RecyclerView
    private lateinit var raidCardViewAdapter: RaidCardViewAdapter

    private lateinit var zippedLiveData: LiveData<Pair<List<Raid>, List<Instances>>>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_raid, container, false)
        raidViewModel = ViewModelProvider.NewInstanceFactory().create(RaidViewModel::class.java)
        try {
            raidViewModel.run {
                fetchRaidData(user.region, user.realmName, user.characterName)
                fetchRaidInstanceData(
                    user.realmName.replace(' ', '-').toLowerCase(Locale.ROOT),
                    user.characterName.toLowerCase(Locale.ROOT)
                )
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        zippedLiveData = LiveDataUtils.zipPair(
            raidViewModel.raid,
            raidViewModel.raidInstances
        )

        raidCardViewAdapter = RaidCardViewAdapter(context, Pair(listOf(), listOf()))

        raidRecyclerView = root.findViewById(R.id.raidRecyclerView)
        raidRecyclerView.run {
            adapter = raidCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zippedLiveData.observe(viewLifecycleOwner, Observer {
            raidCardViewAdapter.raidInstancesList = it
            raidCardViewAdapter.notifyDataSetChanged()
        })
    }
}
