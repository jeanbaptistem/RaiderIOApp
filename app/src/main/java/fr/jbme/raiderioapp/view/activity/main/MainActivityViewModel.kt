package fr.jbme.raiderioapp.view.activity.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SELECTED_CHARACTER
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.service.model.blizzard.AccountProfile
import fr.jbme.raiderioapp.service.model.blizzard.CharacterMedia
import fr.jbme.raiderioapp.service.repository.MainRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.Result
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import java.util.*


class MainActivityViewModel : ViewModel() {
    private var sharedPref: SharedPreferences? =
        RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    private val _profileInfoData = MutableLiveData<AccountProfile>()
    val profileInfoData: LiveData<AccountProfile>
        get() = _profileInfoData

    private val _profileInfoLoading = MutableLiveData<Boolean>()
    val profileInfoLoading: LiveData<Boolean>
        get() = _profileInfoLoading

    private val viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()

    val getSelectedCharacter: LiveData<PopupCharacterItem>
        get() = viewSelectedCharacter

    init {
        _profileInfoLoading.value = true
        MainRepository.fetchProfileInfo(object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                _profileInfoData.value = result.data as AccountProfile
                _profileInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Profile Data error", error.exception.message.toString())
                _profileInfoLoading.value = false
            }
        })
    }

    fun selectedCharacter(selectedCharName: PopupCharacterItem?) {
        if (selectedCharName == null) return
        viewSelectedCharacter.postValue(selectedCharName)
    }

    val updateSharedPref: LiveData<Boolean> =
        Transformations.switchMap(viewSelectedCharacter) { character ->
            with(sharedPref?.edit()) {
                this?.putString(SELECTED_CHARACTER, Gson().toJson(character))
                this?.apply()
            }
            MutableLiveData(true)
        }

    val toolbarCharactersList: LiveData<List<PopupCharacterItem>> =
        Transformations.switchMap(_profileInfoData) { profile ->
            val charactersList = mutableListOf<PopupCharacterItem>()
            profile.wowAccounts?.forEach { account ->
                account?.characters
                    ?.filter { characters -> characters?.level!! >= 100 }
                    ?.forEach { characters ->
                        val characterItem = PopupCharacterItem(characters!!)
                        charactersList.add(characterItem)
                    }
            }
            loadToolbarCharactersList(charactersList.toList())
        }

    private fun loadToolbarCharactersList(charactersList: List<PopupCharacterItem>): LiveData<List<PopupCharacterItem>> {
        val characterListResult = MutableLiveData(charactersList)
        characterListResult.value?.map { characters ->
            MainRepository.fetchCharacterMedia(characters.realmSlug,
                characters.name.toLowerCase(Locale.ROOT), object : DataCallback {
                    override fun onDataLoaded(result: Result.Success<*>) {
                        characters.thumbnailUrl = (result.data as CharacterMedia).avatarUrl!!
                    }

                    override fun onDataNotAvailable(error: Result.Error) {
                        characters.thumbnailUrl = "error"
                    }
                })
        }
        return characterListResult
    }

    val updateSelectedChar = Transformations.switchMap(toolbarCharactersList) {
        val sharedPrefCharacter = MutableLiveData<PopupCharacterItem>()
        sharedPref?.let { sharedPref ->
            sharedPref.getString(SELECTED_CHARACTER, null)?.let { sharedPrefString ->
                sharedPrefCharacter.value =
                    Gson().fromJson(sharedPrefString, PopupCharacterItem::class.java)
            }
        }
        return@switchMap sharedPrefCharacter
    }

    private val characterSearchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = Transformations.distinctUntilChanged(characterSearchQuery)

    fun performCharacterSearch(query: String?) {
        characterSearchQuery.postValue(query)
    }
}