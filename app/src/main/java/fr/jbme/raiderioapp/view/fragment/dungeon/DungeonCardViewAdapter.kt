package fr.jbme.raiderioapp.view.fragment.dungeon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks
import fr.jbme.raiderioapp.view.fragment.dungeon.headerRow.HeaderViewHolder
import fr.jbme.raiderioapp.view.fragment.dungeon.itemRow.DungeonViewHolder

class DungeonCardViewAdapter(
    val context: Context,
    var dungeonList: List<DungeonInfo.BestRun>,
    var ranksList: List<CharacterRanks.Rank>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.dungeon_header_row, parent, false)
            return HeaderViewHolder(context, view)
        } else if (viewType == TYPE_ITEM) {
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.dungeon_item, parent, false)
            return DungeonViewHolder(view)
        }
        throw RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
    }

    override fun getItemCount(): Int {
        return dungeonList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(ranksList)
        } else if (holder is DungeonViewHolder) {
            holder.bind(dungeonList[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}
