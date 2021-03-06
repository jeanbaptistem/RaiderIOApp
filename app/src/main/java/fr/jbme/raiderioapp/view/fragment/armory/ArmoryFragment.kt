package fr.jbme.raiderioapp.view.fragment.armory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.utils.Quadruple
import fr.jbme.raiderioapp.view.activity.main.MainActivityViewModel
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import kotlinx.android.synthetic.main.fragment_armory.*


class ArmoryFragment : Fragment() {
    private val armoryViewModel: ArmoryViewModel by viewModels()

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
                Quadruple(listOf(), listOf(), listOf(), listOf())
            )

        armoryRecyclerView = root.findViewById(R.id.armoryRecyclerView)
        armoryRecyclerView.run {
            adapter = armoryCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        if (arguments?.getSerializable("character") != null) {
            armoryViewModel.selectedCharacter(arguments?.getSerializable("character") as PopupCharacterItem)
        } else {
            val characterViewModel =
                activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }
            characterViewModel?.getSelectedCharacter?.observe(viewLifecycleOwner, Observer {
                armoryViewModel.selectedCharacter(it)
            })
        }

        observeViewModel(armoryViewModel)
        return root
    }

    private fun observeViewModel(viewModel: ArmoryViewModel) {
        viewModel.zippedArmoryLiveData.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.apply {
                itemsData = it
                notifyDataSetChanged()
            }
        })
        viewModel.zippedArmoryDataLoading.observe(viewLifecycleOwner, Observer {
            if (it.first || it.second || it.third) {
                armoryProgressBar.visibility = View.VISIBLE
            } else if (!it.first && !it.second && !it.third) {
                armoryProgressBar.visibility = View.GONE
            }
        })
    }
}
