package fr.jbme.raiderioapp.ui.raid

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.model.login.LoggedInUser
import fr.jbme.raiderioapp.utils.Whatever

class RaidFragment : Fragment() {

    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private lateinit var raidViewModel: RaidViewModel

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
        raidViewModel = ViewModelProvider.NewInstanceFactory().create(RaidViewModel::class.java)
        //val slugRegion = Whatever.parseToSlug(user.region)
        //val slugRealm = Whatever.parseToSlug(user.realmName)
        val slugName = Whatever.parseToSlug(user.accessToken)
        try {
            raidViewModel.run {
                //fetchRaidData(slugRegion, slugRealm, slugName)
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        portraitLayoutManager = LinearLayoutManager(context)
        landscapeLayoutManager = GridLayoutManager(context, 2)

        raidCardViewAdapter = RaidCardViewAdapter(context)//, listOf())

        raidRecyclerView = root.findViewById(R.id.raidRecyclerView)
        raidRecyclerView.run {
            layoutManager = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> landscapeLayoutManager
                Configuration.ORIENTATION_PORTRAIT -> portraitLayoutManager
                else -> portraitLayoutManager
            }
            adapter = raidCardViewAdapter
        }
        return root
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
