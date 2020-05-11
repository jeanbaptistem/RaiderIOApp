package fr.jbme.raiderioapp.view.fragment.armory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.ItemMedia
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )
    private val itemThumbnail: ImageView = itemView.findViewById(R.id.itemThumbnail)
    private val itemThumbnailCardView: CardView = itemView.findViewById(R.id.itemThumbnailCardView)
    private val itemName: TextView = itemView.findViewById(R.id.itemNameTextView)

    private val ilvlTextView: TextView = itemView.findViewById(R.id.itemIlvlTextView)
    private val postGemsTextView: TextView = itemView.findViewById(R.id.postGemsTextView)

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


    fun bindThumbnail(iconThumbnailUrl: String?, itemQuality: String?) {
        Picasso.get().load(iconThumbnailUrl).into(itemThumbnail)
        itemThumbnailCardView.setCardBackgroundColor(
            when (itemQuality) {
                "POOR" -> itemView.context.getColor(R.color.itemQualityPoor)
                "COMMON" -> itemView.context.getColor(R.color.itemQualityCommon)
                "UNCOMMON" -> itemView.context.getColor(R.color.itemQualityUncommon)
                "RARE" -> itemView.context.getColor(R.color.itemQualityRare)
                "EPIC" -> itemView.context.getColor(R.color.itemQualityEpic)
                "LEGENDARY" -> itemView.context.getColor(R.color.itemQualityLegendary)
                "ARTIFACT" -> itemView.context.getColor(R.color.itemQualityArtifact)
                "HEIRLOOM" -> itemView.context.getColor(R.color.itemQualityHeirloom)
                else -> itemView.context.getColor(R.color.primaryTextColor)
            }
        )
    }

    fun bindItem(
        equippedItem: CharacterEquipment.EquippedItem?,
        azeriteEssences: List<ItemMedia>?
    ) {
        itemName.text = equippedItem?.name
        ilvlTextView.text = "Ilvl: ${equippedItem?.level?.value}"
        socketLayoutList.forEach { it.visibility = View.GONE }
        socketImageViewList.forEach { it.visibility = View.GONE }
        postGemsTextView.visibility = View.GONE
        if (equippedItem?.azeriteDetails?.selectedEssences != null) {
            val color = itemView.context.getColor(R.color.itemQualityArtifact)
            equippedItem.azeriteDetails.selectedEssences.filter { it?.slot == 0 }
                .mapIndexed { index, essencePower ->
                    val essenceId = essencePower?.mainSpellTooltip?.spell?.id
                    val essenceThumbnail =
                        azeriteEssences?.firstOrNull { media -> media.id == essenceId }
                    //Picasso.get().load(essenceThumbnail?.assets?.first()?.value).into(socketImageViewList[index])
                    socketImageViewList[index].visibility = View.VISIBLE
                    socketLayoutList[index].run {
                        setCardBackgroundColor(color)
                        visibility = View.VISIBLE
                    }
                }
        } else if (equippedItem?.azeriteDetails?.selectedPowers != null) {
            val color = itemView.context.getColor(R.color.itemQualityLegendary)
            equippedItem.azeriteDetails.selectedPowers.filter { it?.tier == 4 || it?.tier == 3 }
                .mapIndexed { index, power ->
                    val powerId = power?.id
                    //TODO: add power thumbnail
                    //Picasso.get().load(response.body()?.assets?.first()?.value).into(socketImageViewList[index])
                    socketImageViewList[index].visibility = View.VISIBLE
                    socketLayoutList[index].run {
                        setCardBackgroundColor(color)
                        visibility = View.VISIBLE
                    }
                }
        }
    }

    fun bindStats(itemInfo: ItemInfo?) {
        //TODO: add stats
    }

}