package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.content.SharedPreferences
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
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.network.utils.LiveDataUtils


class ArmoryFragment : Fragment() {
    private var sharedPref: SharedPreferences? = null

    private lateinit var armoryViewModel: ArmoryViewModel

    private lateinit var armoryRecyclerView: RecyclerView
    private lateinit var armoryCardViewAdapter: ArmoryCardViewAdapter

    private lateinit var zippedLiveData: LiveData<Triple<List<EquippedItems>, List<ItemInfo>, List<Media>>>

    private var characterName: String? = null
    private var realmSlug: String? = null

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

        sharedPref = context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        sharedPref?.run {
            characterName = getString(CHARACTER_NAME_KEY, null)
            realmSlug = getString(REALM_NAME_KEY, null)
        }

        armoryViewModel = ViewModelProvider.NewInstanceFactory().create(ArmoryViewModel::class.java)
        try {
            armoryViewModel.fetchCharacterEquipment(realmSlug, characterName)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }

        armoryViewModel.characterEquipment.observe(viewLifecycleOwner, Observer {
            try {
                armoryViewModel.fetchItemInfo(it)
                armoryViewModel.fetchItemMedia(it)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        })

        zippedLiveData = LiveDataUtils.zipTriple(
            armoryViewModel.characterEquipment,
            armoryViewModel.itemsInfo,
            armoryViewModel.itemMedia
        )
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zippedLiveData.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.itemsData = it
            armoryCardViewAdapter.notifyDataSetChanged()
        })
    }
}
