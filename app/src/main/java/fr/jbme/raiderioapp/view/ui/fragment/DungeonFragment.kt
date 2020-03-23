package fr.jbme.raiderioapp.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.view.model.DungeonViewModel

class DungeonFragment : Fragment() {

    private lateinit var dungeonViewModel: DungeonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dungeonViewModel =
            ViewModelProvider.NewInstanceFactory().create(DungeonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dungeon, container, false)
        val textView: TextView = root.findViewById(R.id.textDungeon)
        dungeonViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
