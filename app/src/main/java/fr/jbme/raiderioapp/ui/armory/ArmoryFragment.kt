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

class ArmoryFragment : Fragment() {

    private lateinit var armoryViewModel: ArmoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        armoryViewModel =
            ViewModelProviders.of(this).get(ArmoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_armory, container, false)
        val textView: TextView = root.findViewById(R.id.textArmory)
        armoryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
