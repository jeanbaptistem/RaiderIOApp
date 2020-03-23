package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.model.blizzard.itemMedia.Media


class ArmoryCardViewAdapter(
    val context: Context?,
    var itemsData: Triple<List<EquippedItems>, List<ItemInfo>, List<Media>>
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.armory_cardview_row, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val id = itemsData.first[position].item.id
        val equippedItem: EquippedItems? = itemsData.first.find { gear -> gear.item.id == id }
        val itemInfo: ItemInfo? = itemsData.second.find { item -> item.id == id }
        val media: Media? = itemsData.third.find { media -> media.id == id }

        holder.bindThumbnail(media?.assets?.first()?.value, itemInfo?.quality?.type)
        holder.bindItem(equippedItem)
    }

    override fun getItemCount(): Int {
        return itemsData.first.size
    }

}