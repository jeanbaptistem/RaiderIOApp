package fr.jbme.raiderioapp.view.fragment.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.ItemMedia
import fr.jbme.raiderioapp.utils.Quadruple


class ArmoryCardViewAdapter(
    val context: Context?,
    var itemsData: Quadruple<List<CharacterEquipment.EquippedItem>, List<ItemInfo>, List<ItemMedia>, List<ItemMedia>>
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.armory_cardview_row, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val id = itemsData.first[position].item?.id
        val equippedItem: CharacterEquipment.EquippedItem? =
            itemsData.first.find { gear -> gear.item?.id == id }
        val itemInfo: ItemInfo? = itemsData.second.find { item -> item.id == id }
        val media: ItemMedia? = itemsData.third.find { media -> media.id == id }

        holder.bindThumbnail(media?.assets?.first()?.value, equippedItem?.quality?.type)
        holder.bindItem(equippedItem, itemsData.quatro)
        holder.bindStats(itemInfo)
    }

    override fun getItemCount(): Int {
        return itemsData.first.size
    }

}