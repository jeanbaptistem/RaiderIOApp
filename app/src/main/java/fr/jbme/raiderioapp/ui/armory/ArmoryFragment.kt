package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.data.REALM_NAME_KEY
import fr.jbme.raiderioapp.data.REGION_KEY
import fr.jbme.raiderioapp.data.SHARED_PREF_KEY

class ArmoryFragment : Fragment() {

    private var sharedPref: SharedPreferences? = null

    private lateinit var armoryViewModel: ArmoryViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPref = context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        armoryViewModel =
            ViewModelProviders.of(this).get(ArmoryViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_armory, container, false)

        textView = root.findViewById(R.id.textArmory)
        sharedPref?.let {
            val region = it.getString(REGION_KEY, "")
            val realm = it.getString(REALM_NAME_KEY, "")
            val name = it.getString(CHARACTER_NAME_KEY, "")
            armoryViewModel.fetchData(region, realm, name)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        armoryViewModel.gearedCharacter.observe(viewLifecycleOwner, Observer {
            textView.text = it._class
        })
    }
}
