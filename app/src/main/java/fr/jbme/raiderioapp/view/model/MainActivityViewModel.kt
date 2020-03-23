package fr.jbme.raiderioapp.view.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.REALM_NAME_KEY
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.service.repository.MainActivityRepository


class MainActivityViewModel : ViewModel() {
    fun profileInfoObservable(): LiveData<ProfileInfo> {
        return MainActivityRepository.fetchProfileInfo()
    }

    fun characterMediaObservable(): LiveData<CharacterMedia> {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        var realmSlug: String? = null
        var characterName: String? = null
        sharedPref?.let {
            realmSlug = it.getString(REALM_NAME_KEY, null)
            characterName = it.getString(CHARACTER_NAME_KEY, null)
        }
        return MainActivityRepository.fetchCharacterMedia(realmSlug, characterName)
    }

    fun characterProfileObservable(): LiveData<CharacterProfile> {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        var realmSlug: String? = null
        var characterName: String? = null
        sharedPref?.let {
            realmSlug = it.getString(REALM_NAME_KEY, null)
            characterName = it.getString(CHARACTER_NAME_KEY, null)
        }
        return MainActivityRepository.fetchCharacterProfile(realmSlug, characterName)
    }

}