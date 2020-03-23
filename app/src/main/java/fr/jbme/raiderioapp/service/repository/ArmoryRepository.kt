package fr.jbme.raiderioapp.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.characterEquipment.EquippedItems
import fr.jbme.raiderioapp.service.model.blizzard.itemInfo.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.itemMedia.Media
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ArmoryRepository {
    private val globalParamProfile = mapOf("namespace" to "profile-eu", "locale" to "fr_FR")
    private val globalParamItem = mapOf("namespace" to "static-eu", "locale" to "fr_FR")

    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    fun fetchCharacterEquipment(
        realmSlug: String?,
        characterName: String?
    ): LiveData<List<EquippedItems>> {
        val characterEquipment = MutableLiveData<List<EquippedItems>>()

        blizzardService?.getCharacterEquipment(
                realmSlug, characterName,
                globalParamProfile
            )
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
                        characterEquipment.value = response.body()?.equipped_items
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw error
                    }
                }
            })
        return characterEquipment
    }

    fun fetchItemInfo(itemList: List<EquippedItems>): LiveData<List<ItemInfo>> {
        val itemInfo = MutableLiveData<List<ItemInfo>>()
        val tempList = mutableListOf<ItemInfo>()
        itemList.forEach {
            val itemId = it.item.id
            blizzardService?.getItemInfo(
                    itemId,
                    globalParamItem
                )
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
                            itemInfo.value = tempList.toList()
                        } else {
                            val error = NetworkErrorUtils.parseBlizError(response)
                            throw error
                        }
                    }
                })
        }
        return itemInfo
    }

    fun fetchItemMedia(itemList: List<EquippedItems>): LiveData<List<Media>> {
        val itemMedia = MutableLiveData<List<Media>>()
        val tempList = mutableListOf<Media>()
        itemList.forEach {
            val itemId = it.item.id
            blizzardService?.getItemMedia(
                    itemId,
                    globalParamItem
                )
                ?.enqueue(object : Callback<Media> {
                    override fun onFailure(call: Call<Media>, t: Throwable) {
                        throw t
                    }

                    override fun onResponse(
                        call: Call<Media>,
                        response: Response<Media>
                    ) {
                        if (response.isSuccessful) {
                            tempList.add(response.body()!!)
                            itemMedia.value = tempList.toList()

                        } else {
                            val error = NetworkErrorUtils.parseBlizError(response)
                            throw error
                        }
                    }

                })
        }
        return itemMedia
    }
}