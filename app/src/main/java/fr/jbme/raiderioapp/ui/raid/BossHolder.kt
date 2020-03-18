package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.BOSS_ICON_URL
import fr.jbme.raiderioapp.data.model.raidInfo.Encounters
import fr.jbme.raiderioapp.utils.SlugParser

@SuppressLint("SetTextI18n")
class BossHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val raidBossImageView: ImageView = itemView.findViewById(R.id.raidBossImageView)
    private val raidBossKillCounterTextView: TextView =
        itemView.findViewById(R.id.raidBossKillCounterTextView)


    fun bind(encounters: Encounters, raidName: String?) {
        raidBossKillCounterTextView.text = encounters.completed_count.toString() + " kill"
        val boss = SlugParser.parseToSlug(encounters.encounter.name)
        val raid = SlugParser.parseToSlug(raidName)
        val url = BOSS_ICON_URL.format(raid, boss)
        Picasso.get().load(url).into(raidBossImageView)
    }

}