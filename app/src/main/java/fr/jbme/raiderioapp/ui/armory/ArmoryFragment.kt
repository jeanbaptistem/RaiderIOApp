package fr.jbme.raiderioapp.ui.armory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemMediaResponse
import fr.jbme.raiderioapp.data.model.login.LoggedInUser

class ArmoryFragment : Fragment() {

    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private lateinit var armoryViewModel: ArmoryViewModel

    private lateinit var armoryRecyclerView: RecyclerView
    private lateinit var armoryCardViewAdapter: ArmoryCardViewAdapter

    private lateinit var zippedLiveData: LiveData<Triple<List<GearItem>, List<ItemInfoResponse>, List<ItemMediaResponse>>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_armory, container, false)
        armoryViewModel = ViewModelProvider.NewInstanceFactory().create(ArmoryViewModel::class.java)
        try {
            armoryViewModel.fetchCharacterData(user.region, user.realmName, user.characterName)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        armoryViewModel.gear.observe(viewLifecycleOwner, Observer {
            try {
                armoryViewModel.fetchItemInfo(it)
                armoryViewModel.fetchItemMedia(it)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        })

        zippedLiveData =
            zipLiveData(armoryViewModel.gear, armoryViewModel.items, armoryViewModel.medias)

        armoryCardViewAdapter =
            ArmoryCardViewAdapter(context, Triple(listOf(), mutableListOf(), mutableListOf()))

        armoryRecyclerView = root.findViewById(R.id.armoryRecyclerView)
        armoryRecyclerView.run {
            adapter = armoryCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zippedLiveData.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.armoryItems = it
            armoryCardViewAdapter.notifyDataSetChanged()
        })
    }

    private fun <A, B, C> zipLiveData(
        a: LiveData<A>,
        b: LiveData<B>,
        c: LiveData<C>
    ): LiveData<Triple<A, B, C>> {
        return MediatorLiveData<Triple<A, B, C>>().apply {
            var lastA: A? = null
            var lastB: B? = null
            var lastC: C? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                val localLastC = lastC
                if (localLastA != null && localLastB != null && localLastC != null)
                    this.value = Triple(localLastA, localLastB, localLastC)
            }

            addSource(a) {
                lastA = it
                update()
            }
            addSource(b) {
                lastB = it
                update()
            }
            addSource(c) {
                lastC = it
                update()
            }
        }
    }
}
