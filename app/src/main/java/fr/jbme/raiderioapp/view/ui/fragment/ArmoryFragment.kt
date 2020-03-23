package fr.jbme.raiderioapp.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.service.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.utils.LiveDataUtils
import fr.jbme.raiderioapp.view.adapter.ArmoryCardViewAdapter
import fr.jbme.raiderioapp.view.model.ArmoryViewModel


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

        val armoryViewModel =
            ViewModelProvider.NewInstanceFactory().create(ArmoryViewModel::class.java)
        observeViewModel(armoryViewModel)

        return root
    }

    private fun observeViewModel(armoryViewModel: ArmoryViewModel) {
        val characterEquipmentObservable =
            armoryViewModel.characterEquipmentObservable()
        characterEquipmentObservable.observe(viewLifecycleOwner, Observer { equippedItemsList ->
            val zippedLiveData: LiveData<Triple<List<EquippedItems>, List<ItemInfo>, List<Media>>> =
                LiveDataUtils.zipTriple(
                    characterEquipmentObservable,
                    armoryViewModel.itemInfoObservable(equippedItemsList),
                    armoryViewModel.itemMediaObservable(equippedItemsList)
                )
            zippedLiveData.observe(viewLifecycleOwner, Observer { triple ->
                armoryCardViewAdapter.itemsData = triple
                armoryCardViewAdapter.notifyDataSetChanged()
            })
        })

    }
}
