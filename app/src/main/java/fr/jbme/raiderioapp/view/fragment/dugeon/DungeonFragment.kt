package fr.jbme.raiderioapp.view.fragment.dugeon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.R

class DungeonFragment : Fragment() {
    private val dungeonViewModel: DungeonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dungeon, container, false)
        val textView: TextView = root.findViewById(R.id.textDungeon)
        dungeonViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
