package fr.jbme.raiderioapp.view.fragment.search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterScore
import fr.jbme.raiderioapp.view.components.CustomLinearLayout
import java.util.*

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val searchItemThumbnailLayout: CustomLinearLayout =
        itemView.findViewById(R.id.searchItemThumbnailLayout)
    private val searchCharacterName: TextView = itemView.findViewById(R.id.searchCharacterName)
    private val searchCharacterClass: TextView = itemView.findViewById(R.id.searchCharacterClass)
    private val searchCharacterRegion: TextView = itemView.findViewById(R.id.searchCharacterRegion)
    private val searchCharacterRealm: TextView = itemView.findViewById(R.id.searchCharacterRealm)
    private val searchCharacterScoreTextView: TextView =
        itemView.findViewById(R.id.searchCharacterScoreTextView)

    fun bind(character: CharacterScore) {
        Picasso.get().load(character.thumbnailUrl).into(searchItemThumbnailLayout)
        searchCharacterName.text = character.name
        searchCharacterClass.text = character.classX
        searchCharacterRegion.text = "(${character.region?.toUpperCase(Locale.ROOT)})"
        searchCharacterRealm.text = character.realm
        searchCharacterScoreTextView.text =
            (character.mythicPlusScoresBySeason?.first()?.scores?.all ?: 0).toString()
    }

}
