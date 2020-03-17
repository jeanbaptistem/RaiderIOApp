package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemMediaResponse
import kotlin.math.min


class ArmoryCardViewAdapter(
    val context: Context?,
    var armoryItems: Triple<List<GearItem>, List<ItemInfoResponse>, List<ItemMediaResponse>>
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.armory_cardview_row, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val id = armoryItems.first[position].itemId
        val gearItem: GearItem = armoryItems.first.first { gear -> gear.itemId == id }
        val itemInfo: ItemInfoResponse = armoryItems.second.first { item -> item.id == id }
        val media: ItemMediaResponse = armoryItems.third.first { media -> media.id == id }

        holder.bindThumbnail(media.assets.first().value, gearItem)
        holder.bindItem(itemInfo, gearItem)
    }

    override fun getItemCount(): Int {
        return min(armoryItems.first.size, min(armoryItems.second.size, armoryItems.third.size))
    }

}