package fr.jbme.raiderioapp.ui.raid

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.BOSS_ICON_URL
import fr.jbme.raiderioapp.model.BlizzCharacter.Bosses
import fr.jbme.raiderioapp.utils.Whatever

@SuppressLint("SetTextI18n")
class BossHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val raidBossImageView: ImageView = itemView.findViewById(R.id.raidBossImageView)
    private val raidBossKillCounterTextView: TextView =
        itemView.findViewById(R.id.raidBossKillCounterTextView)


    fun bind(bosses: Bosses?, raidId: String?) {
        raidBossKillCounterTextView.text = bosses?.killCount.toString() + " kill"
        var boss = Whatever.parseToSlug(bosses?.name)
        val raid = Whatever.parseToSlug(raidId)
        if (boss?.contains("grong")!!) {
            boss = "grong"
        } else if (boss.contains("zaqul")) {
            boss = "zaqul"
        }
        Picasso.get().load(BOSS_ICON_URL.format(raid, boss)).into(raidBossImageView)
    }

}