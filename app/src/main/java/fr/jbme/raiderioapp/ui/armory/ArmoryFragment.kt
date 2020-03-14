package fr.jbme.raiderioapp.ui.armory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIoApp
import fr.jbme.raiderioapp.data.model.login.LoggedInUser

class ArmoryFragment : Fragment() {

    private val user: LoggedInUser? = RaiderIoApp.loginRepository.user
    private lateinit var armoryViewModel: ArmoryViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        armoryViewModel =
            ViewModelProviders.of(this).get(ArmoryViewModel::class.java)
        armoryViewModel.fetchData(user?.region, user?.realmName, user?.characterName)

        val root = inflater.inflate(R.layout.fragment_armory, container, false)

        // textView = root.findViewById(R.id.textArmory)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        armoryViewModel.gearedCharacter.observe(viewLifecycleOwner, Observer {
            // textView.text = it._class
        })
    }
}
