package fr.jbme.raiderioapp.view.fragment.character.bestRuns

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns.MythicPlusBestRuns
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.view.components.CustomConstraintLayout
import java.util.concurrent.TimeUnit


class BestRunsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val backgroundLayout: CustomConstraintLayout =
        itemView.findViewById(R.id.backgroundLayout)

    private val dungeonName: TextView = itemView.findViewById(R.id.dungeonName)
    private val dungeonLevelTextView: TextView = itemView.findViewById(R.id.dungeonLevelTextView)
    private val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
    private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    private val dungeonUpgrade: ImageView = itemView.findViewById(R.id.dungeonUpgrade)

    private val affxiesList = listOf<CircularImageView>(
        itemView.findViewById(R.id.affixe1),
        itemView.findViewById(R.id.affixe2),
        itemView.findViewById(R.id.affixe3),
        itemView.findViewById(R.id.affixe4)
    )

    @SuppressLint("SimpleDateFormat")
    fun bind(bestRun: MythicPlusBestRuns) {
        dungeonName.text = bestRun.dungeon
        dungeonLevelTextView.text = bestRun.mythic_level.toString()
        scoreTextView.text = bestRun.score.toString()
        timeTextView.text = formatTime(bestRun.clear_time_ms)
        dungeonUpgrade.setImageDrawable(
            when (bestRun.num_keystone_upgrades) {
                0 -> itemView.context.getDrawable(R.drawable.ic_exposure_neg_1_black_24dp)
                1 -> itemView.context.getDrawable(R.drawable.ic_exposure_plus_1_black_24dp)
                2 -> itemView.context.getDrawable(R.drawable.ic_exposure_plus_2_black_24dp)
                3 -> itemView.context.getDrawable(R.drawable.ic_looks_3_black_24dp)
                else -> itemView.context.getDrawable(R.color.primaryColor)
            }
        )
        Picasso.get()
            .load(DUNGEON_BG.format(Whatever.parseToSlug(bestRun.dungeon)))
            .resizeDimen(R.dimen.best_run_cardview_width, R.dimen.best_run_cardview_height)
            .centerCrop(Gravity.CENTER)
            .into(backgroundLayout)

        bestRun.affixes.forEachIndexed { index, affix ->
            val imgUrl = when (affix.name) {
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
            Picasso.get().load(imgUrl).into(affxiesList[index])
        }
    }

    private fun formatTime(clearTimeMs: Long): String? {
        require(clearTimeMs >= 0) { "Duration must be greater than zero!" }
        var millis = clearTimeMs

        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}
