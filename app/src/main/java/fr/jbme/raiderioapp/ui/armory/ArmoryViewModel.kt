package fr.jbme.raiderioapp.ui.armory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.model.blizzard.itemMedia.Media

class ArmoryViewModel : ViewModel() {

    fun characterEquipmentObservable(): LiveData<List<EquippedItems>> {
        var characterName: String? = null
        var realmSlug: String? = null
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        sharedPref?.run {
            characterName = getString(CHARACTER_NAME_KEY, null)
            realmSlug = getString(REALM_NAME_KEY, null)
        }
        return ArmoryRepository.fetchCharacterEquipment(realmSlug, characterName)
    }

    fun itemInfoObservable(itemList: List<EquippedItems>): LiveData<List<ItemInfo>> {
        return ArmoryRepository.fetchItemInfo(itemList)
    }

    fun itemMediaObservable(itemList: List<EquippedItems>): LiveData<List<Media>> {
        return ArmoryRepository.fetchItemMedia(itemList)
    }
}
