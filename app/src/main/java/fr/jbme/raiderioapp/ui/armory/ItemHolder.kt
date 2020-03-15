package fr.jbme.raiderioapp.ui.armory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemThumbnail: ImageView = itemView.findViewById(R.id.itemThumbnail)
    private val itemThumbnailCardView: CardView = itemView.findViewById(R.id.itemThumbnailCardView)
    private val itemName: TextView = itemView.findViewById(R.id.itemNameTextView)
    private val itemDescription: TextView = itemView.findViewById(R.id.itemDescriptionTextView)

    fun bindThumbnail(iconThumbnailUrl: String, gearItem: GearItem) {
        Picasso.get().load(iconThumbnailUrl).into(itemThumbnail)
        itemThumbnailCardView.setCardBackgroundColor(
            when (gearItem.itemQuality) {
                0 -> itemView.resources.getColor(R.color.itemQualityPoor)
                1 -> itemView.resources.getColor(R.color.itemQualityCommon)
                2 -> itemView.resources.getColor(R.color.itemQualityUncommon)
                3 -> itemView.resources.getColor(R.color.itemQualityRare)
                4 -> itemView.resources.getColor(R.color.itemQualityEpic)
                5 -> itemView.resources.getColor(R.color.itemQualityLegendary)
                6 -> itemView.resources.getColor(R.color.itemQualityArtifact)
                7 -> itemView.resources.getColor(R.color.itemQualityHeirloom)
                else -> itemView.resources.getColor(R.color.colorPrimary)
            }
        )
    }

    fun bindItem(itemInfo: ItemInfoResponse) {
        itemName.text = itemInfo.name
        itemDescription.text = itemInfo.id.toString()
    }
}