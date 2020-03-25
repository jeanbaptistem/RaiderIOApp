package fr.jbme.raiderioapp.view.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.service.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.repository.ArmoryRepository
import fr.jbme.raiderioapp.service.repository.DataCallback
import fr.jbme.raiderioapp.utils.LiveDataUtils
import fr.jbme.raiderioapp.utils.Whatever


class ArmoryViewModel : ViewModel() {

    private val _viewSelectedCharacter = MutableLiveData<String>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: String?) {
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    private val _characterArmoryLoading = MutableLiveData<Boolean>()
    private val characterArmory: LiveData<List<EquippedItems>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])!!
            val realm = Whatever.parseToSlug(character.split('-')[1])!!
            Log.i("CharacterArmory", "SwitchMap")
            loadCharacterArmory(realm, name)
        }

    private val _armoryItemInfoLoading = MutableLiveData<Boolean>()
    private val armoryItemData: LiveData<List<ItemInfo>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            Log.i("ArmoryItemData", "SwitchMap")
            loadArmoryItemData(equippedItems)
        }

    private val _armoryItemMediaLoading = MutableLiveData<Boolean>()
    private val armoryItemMedia: LiveData<List<Media>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            Log.i("ArmoryItemMedia", "SwitchMap")
            loadArmoryItemMedia(equippedItems)
        }

    val zippedArmoryLiveData: LiveData<Triple<List<EquippedItems>, List<ItemInfo>, List<Media>>> =
        LiveDataUtils.zipTriple(
            characterArmory,
            armoryItemData,
            armoryItemMedia
        )
    val zippedArmoryDataLoading: LiveData<Triple<Boolean, Boolean, Boolean>> =
        LiveDataUtils.zipTriple(
            _characterArmoryLoading,
            _armoryItemInfoLoading,
            _armoryItemMediaLoading
        )


    private fun loadCharacterArmory(realm: String, name: String): LiveData<List<EquippedItems>> {
        val characterEquipmentResult = MutableLiveData<List<EquippedItems>>()
        _characterArmoryLoading.value = true
        ArmoryRepository.fetchCharacterEquipment(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterEquipmentResult.value = (result.data as CharacterEquipment).equipped_items
                _characterArmoryLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Character armory  error", error.exception.message.toString())
                _characterArmoryLoading.value = false
            }
        })
        return characterEquipmentResult
    }

    private fun loadArmoryItemData(equippedItems: List<EquippedItems>): LiveData<List<ItemInfo>> {
        val itemDataResult = MutableLiveData<List<ItemInfo>>()
        _armoryItemInfoLoading.value = true
        ArmoryRepository.fetchItemInfo(equippedItems, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                itemDataResult.value = result.data as List<ItemInfo>
                _armoryItemInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Item data error", error.exception.message.toString())
                _armoryItemInfoLoading.value = false
            }

        })
        return itemDataResult
    }

    private fun loadArmoryItemMedia(equippedItems: List<EquippedItems>): LiveData<List<Media>> {
        val itemMediaResult = MutableLiveData<List<Media>>()
        _armoryItemMediaLoading.value = true
        ArmoryRepository.fetchItemMedia(equippedItems, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                itemMediaResult.value = result.data as List<Media>
                _armoryItemMediaLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Item media error", error.exception.message.toString())
                _armoryItemMediaLoading.value = false
            }

        })
        return itemMediaResult
    }
}