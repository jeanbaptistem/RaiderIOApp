package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.utils.APIError

class RaidFragment : Fragment() {
    private var sharedPref: SharedPreferences? = null

    private lateinit var raidViewModel: RaidViewModel

    private lateinit var raidRecyclerView: RecyclerView
    private lateinit var raidCardViewAdapter: RaidCardViewAdapter
    private lateinit var portraitLayoutManager: LinearLayoutManager
    private lateinit var landscapeLayoutManager: GridLayoutManager

    private var characterName: String? = null
    private var realmSlug: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_raid, container, false)

        raidCardViewAdapter = RaidCardViewAdapter(context, listOf())

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

        sharedPref = context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        sharedPref?.run {
            characterName = getString(CHARACTER_NAME_KEY, null)
            realmSlug = getString(REALM_NAME_KEY, null)
        }

        raidViewModel = ViewModelProvider.NewInstanceFactory().create(RaidViewModel::class.java)

        try {
            raidViewModel.fetchRaidInfo(realmSlug, characterName)
        } catch (e: APIError) {
            Log.i("apiError", e.message.toString())
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        raidViewModel.raidInfo.observe(viewLifecycleOwner, Observer {
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
