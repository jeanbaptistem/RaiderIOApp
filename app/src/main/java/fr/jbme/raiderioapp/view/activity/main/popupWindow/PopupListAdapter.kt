package fr.jbme.raiderioapp.view.activity.main.popupWindow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import fr.jbme.raiderioapp.R


class PopupListAdapter(val context: Context?, var charList: MutableList<PopupCharacterItem>) :
    BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        if (convertView == null) {
            val view = layoutInflater.inflate(R.layout.custom_character_selection_menu, null)
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
        //Picasso.get().load(characterList[position].thumbnailUrl).into(holder.charThumbnail)

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


}
