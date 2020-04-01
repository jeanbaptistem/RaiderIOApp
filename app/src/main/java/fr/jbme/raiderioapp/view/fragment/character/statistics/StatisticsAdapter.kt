package fr.jbme.raiderioapp.view.fragment.character.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks

class StatisticsAdapter(
    val context: Context?,
    var ranks: CharacterRanks?,
    var worldStats: Boolean?
) : RecyclerView.Adapter<StatisticsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.statistics_card_view, parent, false)
        return StatisticsHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: StatisticsHolder, position: Int) {
        when (position) {
            0 -> {
                fun test(name: String): Boolean {
                    return name.contains(Regex("(class_dps)")) ||
                            name.contains(Regex("(class_healer)")) ||
                            name.contains(Regex("(class_tank)"))
                }

                val rank = ranks?.mythicPlusRanks?.filter {
                    test(it.name!!) && !it.name.contains(Regex("(faction)")) && it.world != 0
                }?.minBy { it -> it.world!! }
                val rankFaction = ranks?.mythicPlusRanks?.filter {
                    test(it.name!!) && it.name.contains(Regex("(faction)")) && it.world != 0
                }?.minBy { it.world!! }
                holder.bind(worldStats!!, rank, rankFaction)
            }
            1 -> {
                fun test(name: String): Boolean {
                    return name.contains(Regex("(class)")) &&
                            (!name.contains(Regex("(dps)")) ||
                                    !name.contains(Regex("(healer)")) ||
                                    !name.contains(Regex("(tank)")))
                }

                val rank = ranks?.mythicPlusRanks?.firstOrNull {
                    test(it.name!!) && !it.name.contains(Regex("(faction)")) && it.world != 0
                }
                val rankFaction = ranks?.mythicPlusRanks?.firstOrNull {
                    test(it.name!!) && it.name.contains(Regex("(faction)")) && it.world != 0
                }
                holder.bind(worldStats!!, rank, rankFaction)
            }
            2 -> {
                fun test(name: String): Boolean {
                    return !name.contains(Regex("(class)")) &&
                            (name.contains(Regex("(dps)")) ||
                                    name.contains(Regex("(healer)")) ||
                                    name.contains(Regex("(tank)")))
                }

                val rank = ranks?.mythicPlusRanks?.filter {
                    test(it.name!!) && !it.name.contains(Regex("(faction)")) && it.world != 0
                }?.minBy { it.world!! }
                val rankFaction = ranks?.mythicPlusRanks?.filter {
                    test(it.name!!) && it.name.contains(Regex("(faction)")) && it.world != 0
                }?.minBy { it.world!! }
                holder.bind(worldStats!!, rank, rankFaction)
            }
            3 -> {
                fun test(name: String): Boolean {
                    return name.contains(Regex("(overall)"))
                }

                val rank = ranks?.mythicPlusRanks?.firstOrNull {
                    test(it.name!!) && !it.name.contains("faction") && it.world != 0
                }
                val rankFaction = ranks?.mythicPlusRanks?.firstOrNull {
                    test(it.name!!) && it.name.contains("faction") && it.world != 0
                }
                holder.bind(worldStats!!, rank, rankFaction)
            }
        }
    }

}
