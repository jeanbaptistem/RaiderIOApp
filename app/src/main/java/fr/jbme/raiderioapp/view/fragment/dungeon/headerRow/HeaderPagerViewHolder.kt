package fr.jbme.raiderioapp.view.fragment.dungeon.headerRow

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.Rank


class HeaderPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val rowList = listOf<TableRow>(
        itemView.findViewById(R.id.row1),
        itemView.findViewById(R.id.row2),
        itemView.findViewById(R.id.row3),
        itemView.findViewById(R.id.row4)
    )

    fun bindTab(ranksList: List<Rank>?) {
        val context = itemView.context
        ranksList?.filter { rank ->
            rank.world != 0 && !rank.name?.contains("faction")!!
        }?.forEachIndexed { index, rank ->
            rowList[index].removeAllViews()
            val ivName = ImageView(context)
            val iconUrl = when {
                rank.name!!.contains("dps") -> IC_DPS
                rank.name.contains("healer") -> IC_HEALER
                rank.name.contains("tank") -> IC_TANK
                else -> IC_FLAG
            }
            Picasso.get().load(iconUrl).into(ivName)
            val tvWorld = TextView(context)
            tvWorld.text = rank.world.toString()
            tvWorld.gravity = Gravity.END
            val tvRegion = TextView(context)
            tvRegion.text = rank.region.toString()
            tvRegion.gravity = Gravity.END
            val tvRealm = TextView(context)
            tvRealm.text = rank.realm.toString()
            tvRealm.gravity = Gravity.END
            rowList[index].run {
                addView(ivName)
                addView(tvWorld)
                addView(tvRegion)
                addView(tvRealm)
            }

        }

    }

}