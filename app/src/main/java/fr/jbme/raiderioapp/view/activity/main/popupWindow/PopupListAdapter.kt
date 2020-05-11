package fr.jbme.raiderioapp.view.activity.main.popupWindow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R


class PopupListAdapter(val context: Context?, var charList: List<PopupCharacterItem>) :
    BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        if (convertView == null) {
            val view =
                layoutInflater.inflate(R.layout.custom_character_selection_menu, parent, false)
            holder =
                ViewHolder(
                    view
                )
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.charName.text = charList[position].name
        holder.charRealm.text = charList[position].realm
        Picasso.get().load(charList[position].thumbnailUrl)
            .placeholder(R.color.primaryColor)
            .error(R.color.errorColor)
            .into(holder.charThumbnail)

        return holder.view
    }

    override fun getItem(position: Int): Any {
        return charList[position]
    }

    override fun getItemId(position: Int): Long {
        return charList[position].id
    }

    override fun getCount(): Int {
        return charList.size
    }

    internal class ViewHolder(val view: View) {
        var charName: TextView = view.findViewById(R.id.charName)
        var charRealm: TextView = view.findViewById(R.id.charRealm)
        var charThumbnail: CircularImageView = view.findViewById(R.id.charThumbnail)
    }

    fun measureContentWidth(): Int {
        var parent: ViewGroup? = null
        var maxWidth = 0
        var itemView: View? = null
        var itemType = 0
        val widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        for (i in 0 until count) {
            val positionType: Int = getItemViewType(i)
            if (positionType != itemType) {
                itemType = positionType
                itemView = null
            }
            if (parent == null) {
                parent = FrameLayout(context!!)
            }
            itemView = getView(i, itemView, parent)
            itemView.measure(widthMeasureSpec, heightMeasureSpec)
            val itemWidth = itemView.measuredWidth
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth
            }
        }
        return maxWidth
    }

}
