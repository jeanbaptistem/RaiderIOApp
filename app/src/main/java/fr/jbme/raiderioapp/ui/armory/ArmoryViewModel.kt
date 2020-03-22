package fr.jbme.raiderioapp.ui.armory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.CharacterEquipment
import fr.jbme.raiderioapp.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.model.blizzard.itemMedia.ItemMedia
import fr.jbme.raiderioapp.network.main.BlizzardService
import fr.jbme.raiderioapp.network.main.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmoryViewModel : ViewModel() {

    private val globalParamProfile = mapOf("namespace" to "profile-eu", "locale" to "fr_FR")
    private val globalParamItem = mapOf("namespace" to "static-eu", "locale" to "fr_FR")


    private var _characterEquipment = MutableLiveData<List<EquippedItems>>()
    val characterEquipment: LiveData<List<EquippedItems>> = _characterEquipment

    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    fun fetchCharacterEquipment(realmSlug: String?, characterName: String?) {
        blizzardService?.getCharacterEquipment(realmSlug, characterName, globalParamProfile)
            ?.enqueue(object :
                Callback<CharacterEquipment> {
                override fun onFailure(call: Call<CharacterEquipment>, t: Throwable) {
                    throw  t
                }

                override fun onResponse(
                    call: Call<CharacterEquipment>,
                    response: Response<CharacterEquipment>
                ) {
                    if (response.isSuccessful) {
                        _characterEquipment.value = response.body()?.equipped_items
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw error
                    }
                }
            })
    }

    private var _itemInfo = MutableLiveData<List<ItemInfo>>()
    val itemsInfo: LiveData<List<ItemInfo>> = _itemInfo

    fun fetchItemInfo(itemList: List<EquippedItems>) {
        val tempList = mutableListOf<ItemInfo>()
        itemList.forEach {
            val itemId = it.item.id
            blizzardService?.getItemInfo(itemId, globalParamItem)
                ?.enqueue(object : Callback<ItemInfo> {
                    override fun onFailure(call: Call<ItemInfo>, t: Throwable) {
                        throw t
                    }

                    override fun onResponse(
                        call: Call<ItemInfo>,
                        response: Response<ItemInfo>
                    ) {
                        if (response.isSuccessful) {
                            tempList.add(response.body()!!)
                            _itemInfo.value = tempList.toList()
                        } else {
                            val error = NetworkErrorUtils.parseBlizError(response)
                            throw error
                        }
                    }
                })
        }
    }

    private var _itemMedia = MutableLiveData<List<ItemMedia>>()
    val itemMedia: LiveData<List<ItemMedia>> = _itemMedia

    fun fetchItemMedia(itemList: List<EquippedItems>) {
        val tempList = mutableListOf<ItemMedia>()
        itemList.forEach {
            val itemId = it.item.id
            blizzardService?.getItemMedia(itemId, globalParamItem)
                ?.enqueue(object : Callback<ItemMedia> {
                    override fun onFailure(call: Call<ItemMedia>, t: Throwable) {
                        throw t
                    }

                    override fun onResponse(
                        call: Call<ItemMedia>,
                        response: Response<ItemMedia>
                    ) {
                        if (response.isSuccessful) {
                            tempList.add(response.body()!!)
                            _itemMedia.value = tempList.toList()

                        } else {
                            val error = NetworkErrorUtils.parseBlizError(response)
                            throw error
                        }
                    }

                })
        }
    }
/*
    fun fetchGemsMedia(gearItemList: List<GearItem>) {
        val tempList = mutableListOf<ItemMedia>()
        gearItemList.forEach { it ->
            if (it.gems!!.isNotEmpty()) {
                it.gems!!.forEach {
                    blizzardService?.getItemMediaInfo(
                            it,
                            BLIZZARD_ACCESS_TOKEN
                        )
                        ?.enqueue(object : Callback<ItemMedia> {
                            override fun onFailure(call: Call<ItemMedia>, t: Throwable) {
                                throw APIError(t.message)
                            }

                            override fun onResponse(
                                call: Call<ItemMedia>,
                                response: Response<ItemMedia>
                            ) {
                                if (response.isSuccessful) {
                                    tempList.add(response.body()!!)
                                    _gems.value = tempList.toList()
                                } else {
                                    val error = NetworkErrorUtils.parseBlizError(response)
                                    throw error
                                }
                            }
                        })
                }
            } else {
                _gems.value = listOf()
            }
        }
    }*/
}
