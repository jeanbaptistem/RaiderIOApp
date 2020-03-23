package fr.jbme.raiderioapp.ui.armory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.network.main.BlizzardService
import fr.jbme.raiderioapp.network.main.RetrofitBlizzardInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )
    private val globalParamItem = mapOf("namespace" to "static-eu", "locale" to "fr_FR")

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
                else -> itemView.context.getColor(R.color.colorPrimary)
            }
        )
    }

    fun bindItem(equippedItem: EquippedItems?) {
        itemName.text = equippedItem?.name
        ilvlTextView.text = "Ilvl: ${equippedItem?.level?.value}"
        socketLayoutList.forEach { it.visibility = View.GONE }
        socketImageViewList.forEach { it.visibility = View.GONE }
        postGemsTextView.visibility = View.GONE
        if (equippedItem?.azerite_details?.selected_essences != null) {
            val color = itemView.context.getColor(R.color.itemQualityArtifact)
            equippedItem.azerite_details.selected_essences.filter { it.slot == 0 }
                .mapIndexed { index, essencePower ->
                    val essenceId = essencePower.essence.id
                    blizzardService?.getAzeriteEssenceMedia(essenceId, globalParamItem)
                        ?.enqueue(object : Callback<Media> {
                            override fun onFailure(call: Call<Media>, t: Throwable) {
                                throw t
                            }

                            override fun onResponse(
                                call: Call<Media>,
                                response: Response<Media>
                            ) {
                                if (response.isSuccessful) {
                                    Picasso.get()
                                        .load(response.body()?.assets?.first()?.value)
                                        .into(socketImageViewList[index])
                                }
                            }
                        })
                    socketImageViewList[index].visibility = View.VISIBLE
                    socketLayoutList[index].run {
                        setCardBackgroundColor(color)
                        visibility = View.VISIBLE
                    }
                }
        }
    }
}