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
import fr.jbme.raiderioapp.service.model.raiderio.CharacterBestRuns
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.view.components.CustomConstraintLayout


class BestRunsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val backgroundLayout: CustomConstraintLayout =
        itemView.findViewById(R.id.backgroundLayout)

    private val dungeonName: TextView = itemView.findViewById(R.id.dungeonName)
    private val dungeonLevelTextView: TextView = itemView.findViewById(R.id.dungeonLevelTextView)
    private val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
    private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    private val dungeonUpgrade: ImageView = itemView.findViewById(R.id.dungeonUpgrade)

    private val affixesList = listOf<CircularImageView>(
        itemView.findViewById(R.id.affixe1),
        itemView.findViewById(R.id.affixe2),
        itemView.findViewById(R.id.affixe3),
        itemView.findViewById(R.id.affixe4)
    )

    @SuppressLint("SimpleDateFormat")
    fun bind(bestRun: CharacterBestRuns.MythicPlusBestRun) {
        dungeonName.text = bestRun.dungeon
        dungeonLevelTextView.text = bestRun.mythicLevel.toString()
        scoreTextView.text = bestRun.score.toString()
        timeTextView.text = Whatever.formatTime(bestRun.clearTimeMs!!)
        dungeonUpgrade.setImageDrawable(
            when (bestRun.numKeystoneUpgrades) {
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

        bestRun.affixes?.forEachIndexed { index, affix ->
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
            Picasso.get().load(imgUrl).into(affixesList[index])
        }
    }
}
