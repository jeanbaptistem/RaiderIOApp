package fr.jbme.raiderioapp.view.fragment.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterScore

class SearchAdapter(
    val context: Context?,
    var searchList: List<CharacterScore>
) : RecyclerView.Adapter<SearchHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.search_card_view_row, parent, false)
        return SearchHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(searchList[position])
    }

}
