package fr.jbme.raiderioapp.view.fragment.armory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.ItemMedia
import fr.jbme.raiderioapp.service.repository.ArmoryRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Quadruple
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.utils.network.Result
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import java.util.*


@Suppress("UNCHECKED_CAST")
class ArmoryViewModel : ViewModel() {

    private val _viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: PopupCharacterItem?) {
        Log.i("SelectedChar", selectedCharName.toString())
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    private val _characterArmoryLoading = MutableLiveData<Boolean>()
    private val characterArmory: LiveData<List<CharacterEquipment.EquippedItem>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            loadCharacterArmory(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private val _armoryItemInfoLoading = MutableLiveData<Boolean>()
    private val armoryItemData: LiveData<List<ItemInfo>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadArmoryItemData(equippedItems)
        }

    private val _armoryItemMediaLoading = MutableLiveData<Boolean>()
    private val armoryItemMedia: LiveData<List<ItemMedia>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadArmoryItemMedia(equippedItems)
        }

    private val _armoryAzeriteSpellLoading = MutableLiveData<Boolean>()
    private val armoryAzeriteSpell: LiveData<List<ItemMedia>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadAzeriteItemMedia(equippedItems)
        }

    val zippedArmoryLiveData: LiveData<Quadruple<List<CharacterEquipment.EquippedItem>, List<ItemInfo>, List<ItemMedia>, List<ItemMedia>>> =
        Whatever.zipQuadruple(
            characterArmory,
            armoryItemData,
            armoryItemMedia,
            armoryAzeriteSpell
        )
    val zippedArmoryDataLoading: LiveData<Quadruple<Boolean, Boolean, Boolean, Boolean>> =
        Whatever.zipQuadruple(
            _characterArmoryLoading,
            _armoryItemInfoLoading,
            _armoryItemMediaLoading,
            _armoryAzeriteSpellLoading
        )


    private fun loadCharacterArmory(
        realm: String,
        name: String
    ): LiveData<List<CharacterEquipment.EquippedItem>> {
        val characterEquipmentResult = MutableLiveData<List<CharacterEquipment.EquippedItem>>()
        _characterArmoryLoading.value = true
        ArmoryRepository.fetchCharacterEquipment(realm, name, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterEquipmentResult.value =
                    (result.data as CharacterEquipment).equippedItems as List<CharacterEquipment.EquippedItem>?
                _characterArmoryLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i(
                    "Character armory  error",
                    error.exception.message.toString()
                )
                _characterArmoryLoading.value = true
            }
        })
        return characterEquipmentResult
    }

    private fun loadArmoryItemData(equippedItems: List<CharacterEquipment.EquippedItem>): LiveData<List<ItemInfo>> {
        val itemDataResult = MutableLiveData<List<ItemInfo>>()
        _armoryItemInfoLoading.value = true
        ArmoryRepository.fetchItemInfo(equippedItems, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                itemDataResult.value = result.data as List<ItemInfo>
                _armoryItemInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Item data error", error.exception.message.toString())
                _armoryItemInfoLoading.value = true
            }
        })
        return itemDataResult
    }

    private fun loadArmoryItemMedia(equippedItems: List<CharacterEquipment.EquippedItem>): LiveData<List<ItemMedia>> {
        val itemMediaResult = MutableLiveData<List<ItemMedia>>()
        _armoryItemMediaLoading.value = true
        ArmoryRepository.fetchItemMedia(equippedItems, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                itemMediaResult.value = result.data as List<ItemMedia>
                _armoryItemMediaLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Item media error", error.exception.message.toString())
                _armoryItemMediaLoading.value = true
            }

        })
        return itemMediaResult
    }

    private fun loadAzeriteItemMedia(equippedItems: List<CharacterEquipment.EquippedItem>): LiveData<List<ItemMedia>> {
        val armoryAzeriteSpellResult = MutableLiveData<List<ItemMedia>>()
        _armoryAzeriteSpellLoading.value = true
        ArmoryRepository.fetchAzeriteSpellMedia(equippedItems, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                armoryAzeriteSpellResult.value = result.data as List<ItemMedia>
                _armoryAzeriteSpellLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Azerite media error", error.exception.message.toString())
                _armoryAzeriteSpellLoading.value = true
            }
        })
        return armoryAzeriteSpellResult
    }
}