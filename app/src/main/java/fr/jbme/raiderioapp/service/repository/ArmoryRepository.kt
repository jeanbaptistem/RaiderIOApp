package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.service.model.blizzard.CharacterEquipment
import fr.jbme.raiderioapp.service.model.blizzard.ItemInfo
import fr.jbme.raiderioapp.service.model.blizzard.ItemMedia
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.NetworkUtils
import fr.jbme.raiderioapp.utils.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ArmoryRepository {
    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    fun fetchCharacterEquipment(
        realmSlug: String?,
        characterName: String?,
        callback: DataCallback
    ) {
        blizzardService?.getCharacterEquipmentBliz(realmSlug, characterName)
            ?.enqueue(object : Callback<CharacterEquipment> {
                override fun onFailure(call: Call<CharacterEquipment>, t: Throwable) {
                    throw  t
                }

                override fun onResponse(
                    call: Call<CharacterEquipment>,
                    response: Response<CharacterEquipment>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!))
                    } else {
                        val error = NetworkUtils.parseBlizError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchItemInfo(itemList: List<CharacterEquipment.EquippedItem>, callback: DataCallback) {
        val tempList = mutableListOf<ItemInfo>()
        itemList.forEach {
            val itemId = it.item?.id
            blizzardService?.getItemInfoBliz(itemId)
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
                            callback.onDataLoaded(Result.Success(tempList.toList()))
                        } else {
                            val error = NetworkUtils.parseBlizError(response)
                            callback.onDataNotAvailable(Result.Error(error))
                        }
                    }
                })
        }
    }

    fun fetchItemMedia(itemList: List<CharacterEquipment.EquippedItem>, callback: DataCallback) {
        val tempList = mutableListOf<ItemMedia>()
        itemList.forEach {
            val itemId = it.item?.id
            blizzardService?.getItemMediaBliz(itemId)
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
                            callback.onDataLoaded(Result.Success(tempList.toList()))

                        } else {
                            val error = NetworkUtils.parseBlizError(response)
                            callback.onDataNotAvailable(Result.Error(error))
                        }
                    }

                })
        }
    }

    fun fetchAzeriteSpellMedia(
        itemList: List<CharacterEquipment.EquippedItem>,
        callback: DataCallback
    ) {
        val tempList = mutableListOf<ItemMedia>()
        itemList.forEach { equippedItems ->
            equippedItems.azeriteDetails?.selectedEssences?.forEach {
                blizzardService?.getAzeriteEssenceMediaBliz(it?.essence?.id!!)
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
                                callback.onDataLoaded(Result.Success(tempList.toList()))

                            } else {
                                val error = NetworkUtils.parseBlizError(response)
                                callback.onDataNotAvailable(Result.Error(error))
                            }
                        }

                    })
            }
        }

    }
}
