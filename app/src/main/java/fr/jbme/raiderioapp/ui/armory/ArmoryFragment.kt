package fr.jbme.raiderioapp.ui.armory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.utils.APIError

class ArmoryFragment : Fragment() {

    private val user: LoggedInUser? = RaiderIOApp.loginRepository.user
    private lateinit var armoryViewModel: ArmoryViewModel

    private lateinit var armoryRecyclerView: RecyclerView
    private lateinit var armoryCardViewAdapter: ArmoryCardViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_armory, container, false)
        armoryViewModel = ViewModelProvider.NewInstanceFactory().create(ArmoryViewModel::class.java)
        armoryViewModel.fetchCharacterData(
            user?.region!!,
            user.realmName,
            user.characterName,
            this::displayApiError
        )

        armoryCardViewAdapter = ArmoryCardViewAdapter(context, listOf())

        armoryRecyclerView = root.findViewById(R.id.armoryRecyclerView)
        armoryRecyclerView.run {
            adapter = armoryCardViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        armoryViewModel.gear.observe(viewLifecycleOwner, Observer {
            armoryCardViewAdapter.gearItems = it
            armoryCardViewAdapter.notifyDataSetChanged()
        })
    }

    private fun displayApiError(e: APIError) {
        view?.let {
            Snackbar.make(
                    it.findViewById(R.id.armoryRecyclerView),
                    e.message.toString(),
                    Snackbar.LENGTH_SHORT
                )
                .setAction("Action", null).show()
        }
    }
}
