package fr.jbme.raiderioapp.ui.dungeon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.jbme.raiderioapp.R

class DungeonFragment : Fragment() {

    private lateinit var dungeonViewModel: DungeonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dungeonViewModel =
            ViewModelProviders.of(this).get(DungeonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dungeon, container, false)
        val textView: TextView = root.findViewById(R.id.textDungeon)
        dungeonViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
