package fr.jbme.raiderioapp.view.fragment.armory

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
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Quadruple
import fr.jbme.raiderioapp.utils.Whatever


@Suppress("UNCHECKED_CAST")
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
            loadCharacterArmory(realm, name)
        }

    private val _armoryItemInfoLoading = MutableLiveData<Boolean>()
    private val armoryItemData: LiveData<List<ItemInfo>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadArmoryItemData(equippedItems)
        }

    private val _armoryItemMediaLoading = MutableLiveData<Boolean>()
    private val armoryItemMedia: LiveData<List<Media>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadArmoryItemMedia(equippedItems)
        }

    private val _armoryAzeriteSpellLoading = MutableLiveData<Boolean>()
    private val armoryAzeriteSpell: LiveData<List<Media>> =
        Transformations.switchMap(characterArmory) { equippedItems ->
            loadAzeriteItemMedia(equippedItems)
        }

    val zippedArmoryLiveData: LiveData<Quadruple<List<EquippedItems>, List<ItemInfo>, List<Media>, List<Media>>> =
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


    private fun loadCharacterArmory(realm: String, name: String): LiveData<List<EquippedItems>> {
        val characterEquipmentResult = MutableLiveData<List<EquippedItems>>()
        _characterArmoryLoading.value = true
        ArmoryRepository.fetchCharacterEquipment(realm, name, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterEquipmentResult.value = (result.data as CharacterEquipment).equipped_items
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

    private fun loadArmoryItemData(equippedItems: List<EquippedItems>): LiveData<List<ItemInfo>> {
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

    private fun loadArmoryItemMedia(equippedItems: List<EquippedItems>): LiveData<List<Media>> {
        val itemMediaResult = MutableLiveData<List<Media>>()
        _armoryItemMediaLoading.value = true
        ArmoryRepository.fetchItemMedia(equippedItems, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                itemMediaResult.value = result.data as List<Media>
                _armoryItemMediaLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Item media error", error.exception.message.toString())
                _armoryItemMediaLoading.value = true
            }

        })
        return itemMediaResult
    }

    private fun loadAzeriteItemMedia(equippedItems: List<EquippedItems>): LiveData<List<Media>> {
        val armoryAzeriteSpellResult = MutableLiveData<List<Media>>()
        _armoryAzeriteSpellLoading.value = true
        val azeriteItems =
            equippedItems.filter { item -> item.azerite_details?.selected_essences != null }
        ArmoryRepository.fetchAzeriteSpellMedia(azeriteItems, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                armoryAzeriteSpellResult.value = result.data as List<Media>
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