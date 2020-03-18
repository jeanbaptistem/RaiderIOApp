package fr.jbme.raiderioapp.ui.armory

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
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.utils.LiveDataUtils
import fr.jbme.raiderioapp.network.utils.Quadruple

class ArmoryFragment : Fragment() {

    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private lateinit var armoryViewModel: ArmoryViewModel

    private lateinit var armoryRecyclerView: RecyclerView
    private lateinit var armoryCardViewAdapter: ArmoryCardViewAdapter

    private lateinit var zippedLiveData: LiveData<Quadruple<List<GearItem>, List<ItemInfoResponse>, List<BlizMediaResponse>, List<BlizMediaResponse>>>

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
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        armoryViewModel.gear.observe(viewLifecycleOwner, Observer {
            try {
                armoryViewModel.fetchItemInfo(it)
                armoryViewModel.fetchItemMedia(it)
                armoryViewModel.fetchGemsMedia(it)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        })

        zippedLiveData = LiveDataUtils.zipQuadruple(
            armoryViewModel.gear,
            armoryViewModel.items,
            armoryViewModel.medias,
            armoryViewModel.gems
        )

        armoryCardViewAdapter =
            ArmoryCardViewAdapter(context, Quadruple(listOf(), listOf(), listOf(), listOf()))

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
}
