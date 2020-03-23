package fr.jbme.raiderioapp.ui.raid

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.blizzard.raidInfo.Instances

class RaidViewModel : ViewModel() {

    fun raidInfoObservable(): LiveData<List<Instances>> {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        var realmSlug: String? = null
        var characterName: String? = null
        sharedPref?.run {
            realmSlug = getString(REALM_NAME_KEY, null)

            characterName = getString(CHARACTER_NAME_KEY, null)
        }
        return RaidRepository.fetchRaidInfo(realmSlug, characterName)
    }
}