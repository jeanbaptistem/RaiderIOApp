package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.model.RIOCharacter.GearItem
import fr.jbme.raiderioapp.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.utils.Quadruple
import kotlin.math.min


class ArmoryCardViewAdapter(
    val context: Context?,
    var armoryItems: Quadruple<List<GearItem>, List<ItemInfoResponse>, List<BlizMediaResponse>, List<BlizMediaResponse>>
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.armory_cardview_row, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val id = armoryItems.first[position].itemId
        val gearItem: GearItem? = armoryItems.first.find { gear -> gear.itemId == id }
        val itemInfo: ItemInfoResponse? = armoryItems.second.find { item -> item.id == id }
        val media: BlizMediaResponse? = armoryItems.third.find { media -> media.id == id }
        val gems: List<BlizMediaResponse>? = armoryItems.quadro

        holder.bindThumbnail(media?.assets?.first()?.value, gearItem)
        holder.bindItem(itemInfo, gearItem, gems)
    }

    override fun getItemCount(): Int {
        return min(armoryItems.first.size, min(armoryItems.second.size, armoryItems.third.size))
    }

}