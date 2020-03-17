package fr.jbme.raiderioapp.ui.armory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.ICON_DEFAULT_URL
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemThumbnail: ImageView = itemView.findViewById(R.id.itemThumbnail)
    private val itemThumbnailCardView: CardView = itemView.findViewById(R.id.itemThumbnailCardView)
    private val itemName: TextView = itemView.findViewById(R.id.itemNameTextView)

    private val ilvlTextView: TextView = itemView.findViewById(R.id.itemIlvlTextView)

    private val socketLayoutList = listOf<CardView>(
        itemView.findViewById(R.id.cardSocket_1),
        itemView.findViewById(R.id.cardSocket_2),
        itemView.findViewById(R.id.cardSocket_3)
    )
    private val socketImageViewList = listOf<ImageView>(
        itemView.findViewById(R.id.imageViewSocket_1),
        itemView.findViewById(R.id.imageViewSocket_2),
        itemView.findViewById(R.id.imageViewSocket_3)
    )


    fun bindThumbnail(iconThumbnailUrl: String?, gearItem: GearItem?) {
        Picasso.get().load(iconThumbnailUrl).into(itemThumbnail)
        itemThumbnailCardView.setCardBackgroundColor(
            when (gearItem?.itemQuality) {
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

    fun bindItem(itemInfo: ItemInfoResponse?, gearItem: GearItem?, gems: List<BlizMediaResponse>?) {
        itemName.text = itemInfo?.name
        ilvlTextView.text = "Ilvl: " + gearItem?.itemLevel.toString()
        socketLayoutList.forEach { it.visibility = View.GONE }
        socketImageViewList.forEach { it.visibility = View.GONE }
        when {
            gearItem?.isAzeriteArmor!! -> {
                val color = itemView.resources.getColor(R.color.itemQualityArtifact)
                socketLayoutList.subList(0, 2).forEach {
                    it.setCardBackgroundColor(color)
                    it.visibility = View.VISIBLE
                }
                gearItem.azeritePowers!!
                    .filter { it.tier == 3 }
                    .mapIndexed { index, azeritePower ->
                        Picasso.get()
                            .load(ICON_DEFAULT_URL + azeritePower.spell!!.icon + ".jpg")
                            .into(socketImageViewList[index])
                        socketImageViewList[index].visibility = View.VISIBLE
                    }
            }
            gearItem.gems!!.isNotEmpty() -> {
                val color = itemView.resources.getColor(R.color.itemQualityEpic)
                gearItem.gems!!.mapIndexed { index, gemId ->
                    with(socketLayoutList[index]) {
                        setCardBackgroundColor(color)
                        visibility = View.VISIBLE
                    }
                    with(socketImageViewList[index]) {
                        gems?.first { gem -> gem.id == gemId }.let {
                            Picasso.get().load(it?.assets?.first()?.value).into(this)
                        }
                        visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}