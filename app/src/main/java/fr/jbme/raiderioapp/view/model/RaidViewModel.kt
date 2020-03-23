package fr.jbme.raiderioapp.view.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.REALM_NAME_KEY
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.Instances
import fr.jbme.raiderioapp.service.model.raiderio.RaidProgression
import fr.jbme.raiderioapp.service.repository.RaidRepository

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

    fun raidInfoRioObservable(): LiveData<RaidProgression> {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        var realmSlug: String? = null
        var characterName: String? = null
        sharedPref?.run {
            realmSlug = getString(REALM_NAME_KEY, null)
            characterName = getString(CHARACTER_NAME_KEY, null)
        }
        return RaidRepository.fetchRaidInfoRio(realmSlug, characterName)
    }
}