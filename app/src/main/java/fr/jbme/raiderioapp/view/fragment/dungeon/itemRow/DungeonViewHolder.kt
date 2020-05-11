package fr.jbme.raiderioapp.view.fragment.dungeon.itemRow

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.view.components.CustomConstraintLayout
import java.util.*

@SuppressLint("SetTextI18n")
class DungeonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val layout: CustomConstraintLayout = itemView.findViewById(R.id.dungeonLayout)
    private val dungeonLevel: TextView = itemView.findViewById(R.id.dungeonLevelTextView)
    private val dungeonName: TextView = itemView.findViewById(R.id.dungeonName)
    private val dungeonDate: TextView = itemView.findViewById(R.id.dungeonDate)
    private val dungeonTime: TextView = itemView.findViewById(R.id.dungeonTime)
    private val affixesCardView = listOf<CardView>(
        itemView.findViewById(R.id.affixesCardView1),
        itemView.findViewById(R.id.affixesCardView2),
        itemView.findViewById(R.id.affixesCardView3),
        itemView.findViewById(R.id.affixesCardView4)
    )
    private val affixesImageView = listOf<ImageView>(
        itemView.findViewById(R.id.affixesImageView1),
        itemView.findViewById(R.id.affixesImageView3),
        itemView.findViewById(R.id.affixesImageView2),
        itemView.findViewById(R.id.affixesImageView4)
    )
    private val dateFormat = DateFormat.getMediumDateFormat(RaiderIOApp.context)

    private val memberRecyclerView: RecyclerView =
        itemView.findViewById(R.id.dungeonMembersRecyclerView)
    private val memberAdapterView = MemberAdapterView(itemView.context, listOf())

    init {
        memberRecyclerView.run {
            adapter = memberAdapterView
            layoutManager = LinearLayoutManager(itemView.context)
        }
    }

    fun bind(bestRuns: DungeonInfo.BestRun) {
        dungeonLevel.text = "+${bestRuns.keystoneLevel}"
        dungeonName.text = bestRuns.dungeon?.name
        dungeonDate.text = "Cleared ${dateFormat.format(Date(bestRuns.completedTimestamp!!))}"
        dungeonTime.text = when (bestRuns.isCompletedWithinTime!!) {
            true -> "Timed in ${Whatever.formatTime(bestRuns.duration!!.toLong())}"
            false -> "Depleted in ${Whatever.formatTime(bestRuns.duration!!.toLong())}"
        }
        bestRuns.keystoneAffixes?.forEachIndexed { index, affix ->
            val imgUrl = when (affix?.name) {
                "Skittish" -> SKITTISH
                "Volcanic" -> VOLCANIC
                "Necrotic" -> NECROTIC
                "Teeming" -> TEEMING
                "Raging" -> RAGING
                "Bolstering" -> BOLSTERING
                "Sanguine" -> SANGUINE
                "Tyrannical" -> TYRANNICAL
                "Fortified" -> FORTIFIED
                "Bursting" -> BURSTING
                "Grievous" -> GRIEVOUS
                "Explosive" -> EXPLOSIVE
                "Quaking" -> QUAKING
                "Awakened" -> AWAKENED
                else -> "null"
            }
            Picasso.get().load(imgUrl)
                .into(affixesImageView[index])
        }

        Picasso.get()
            .load(DUNGEON_BG.format(Whatever.parseToSlug(bestRuns.dungeon?.name)))
            .into(layout)

        memberAdapterView.run {
            // memberList = bestRuns.members
        }

    }
}
