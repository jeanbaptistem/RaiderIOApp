package fr.jbme.raiderioapp.view.fragment.dungeon.itemRow

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo

class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val roleIcon: ImageView = itemView.findViewById(R.id.roleIconImageView)
    private val memberName: TextView = itemView.findViewById(R.id.memberNameTextView)
    private val memberRealm: TextView = itemView.findViewById(R.id.memberRealmTextView)
    private val memberSpec: TextView = itemView.findViewById(R.id.memberSpecTextView)
    private val memberIlvl: TextView = itemView.findViewById(R.id.memberIlvlTextView)

    fun bind(member: DungeonInfo.BestRun.Member) {
        memberName.text = member.character?.name
        memberRealm.text = member.character?.realm?.slug
        memberSpec.text = member.specialization?.name
        memberIlvl.text = "Ilvl: ${member.equippedItemLevel}"
        roleIcon.setImageDrawable(
            when (member.specialization?.id) {
                1 -> itemView.context.getDrawable(R.drawable.ic_security_white_24dp)
                2 -> itemView.context.getDrawable(R.drawable.ic_local_hospital_white_24dp)
                3 -> itemView.context.getDrawable(R.drawable.ic_whatshot_white_24dp)
                else -> itemView.context.getDrawable(R.drawable.ic_error_white_24dp)
            }
        )
    }
}
