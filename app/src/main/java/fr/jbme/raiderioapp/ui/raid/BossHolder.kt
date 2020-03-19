package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.BOSS_ICON_URL
import fr.jbme.raiderioapp.data.model.wow.character.Bosses
import fr.jbme.raiderioapp.utils.SlugParser

@SuppressLint("SetTextI18n")
class BossHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val raidBossImageView: ImageView = itemView.findViewById(R.id.raidBossImageView)
    private val raidBossKillCounterTextView: TextView =
        itemView.findViewById(R.id.raidBossKillCounterTextView)


    fun bind(bosses: Bosses?, raidId: String?) {
        raidBossKillCounterTextView.text = bosses?.killCount.toString() + " kill"
        var boss = SlugParser.parseToSlug(bosses?.name)
        val raid = SlugParser.parseToSlug(raidId)
        if (boss?.contains("grong")!!) {
            boss = "grong"
        } else if (boss.contains("zaqul")) {
            boss = "zaqul"
        }
        val url = BOSS_ICON_URL.format(raid, boss)
        Picasso.get().load(url).error(R.color.colorWarn).into(raidBossImageView)
    }

}