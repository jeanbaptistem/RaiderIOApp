package fr.jbme.raiderioapp.ui.raid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.utils.SlugParser

class RaidFragment : Fragment() {

    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private lateinit var raidViewModel: RaidViewModel

    private lateinit var raidRecyclerView: RecyclerView
    private lateinit var raidCardViewAdapter: RaidCardViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_raid, container, false)
        raidViewModel = ViewModelProvider.NewInstanceFactory().create(RaidViewModel::class.java)
        val slugRegion = SlugParser.parseToSlug(user.region)
        val slugRealm = SlugParser.parseToSlug(user.realmName)
        val slugName = SlugParser.parseToSlug(user.characterName)
        try {
            raidViewModel.run {
                fetchRaidData(slugRegion, slugRealm, slugName)
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }


        raidCardViewAdapter = RaidCardViewAdapter(context, listOf())

        raidRecyclerView = root.findViewById(R.id.raidRecyclerView)
        raidRecyclerView.run {
            adapter = raidCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        raidViewModel.raid.observe(viewLifecycleOwner, Observer {
            Log.i("RaidsList", it.toString())
            raidCardViewAdapter.raidList = it.asReversed()
            raidCardViewAdapter.notifyDataSetChanged()
        })
    }
}
