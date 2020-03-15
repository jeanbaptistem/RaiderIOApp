package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.character.GearItem


class ArmoryCardViewAdapter(val context: Context?, var gearItems: List<GearItem>) :
    RecyclerView.Adapter<ItemHolder>() {

    private lateinit var itemInfoViewModel: ItemInfoViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.armory_cardview_row, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val gearItem: GearItem = gearItems[position]
        itemInfoViewModel =
            ViewModelProvider.NewInstanceFactory().create(ItemInfoViewModel::class.java)
        itemInfoViewModel.fetchItemIconUrl(gearItem.itemId!!)
        itemInfoViewModel.iconUrl.observe(context as LifecycleOwner, Observer {
            holder.bindThumbnail(it, gearItem)
        })
        itemInfoViewModel.fetchItemInfo(gearItem.itemId!!)
        itemInfoViewModel.itemInfo.observe(context, Observer {
            holder.bindItem(it)
        })
    }

    override fun getItemCount(): Int {
        return gearItems.size
    }
}