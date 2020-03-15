package fr.jbme.raiderioapp.ui.raid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.jbme.raiderioapp.R

class RaidFragment : Fragment() {

    private lateinit var raidViewModel: RaidViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        raidViewModel = ViewModelProvider.NewInstanceFactory().create(RaidViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_raid, container, false)
        val textView: TextView = root.findViewById(R.id.textRaid)
        raidViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
