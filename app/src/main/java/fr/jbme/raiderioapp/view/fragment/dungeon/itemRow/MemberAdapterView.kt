package fr.jbme.raiderioapp.view.fragment.dungeon.itemRow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo

class MemberAdapterView(val context: Context?, var memberList: List<DungeonInfo.BestRun.Member>) :
    RecyclerView.Adapter<MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.dungeon_member_row, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(memberList[position])
    }

}
