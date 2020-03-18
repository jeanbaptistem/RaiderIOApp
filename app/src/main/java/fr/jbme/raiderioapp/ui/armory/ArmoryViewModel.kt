package fr.jbme.raiderioapp.ui.armory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.BLIZZARD_ACCESS_TOKEN
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.character.Gear
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.data.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.network.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.BlizzardService
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.network.utils.APIError
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmoryViewModel : ViewModel() {

    private var _gear = MutableLiveData<List<GearItem>>()
    val gear: LiveData<List<GearItem>> = _gear

    private var _items = MutableLiveData<List<ItemInfoResponse>>()
    val items: LiveData<List<ItemInfoResponse>> = _items

    private var _medias = MutableLiveData<List<BlizMediaResponse>>()
    val medias: LiveData<List<BlizMediaResponse>> = _medias

    private var _gems = MutableLiveData<List<BlizMediaResponse>>()
    val gems: LiveData<List<BlizMediaResponse>> = _gems


    private var raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )
    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    fun fetchCharacterData(
        region: String,
        realm: String,
        name: String
    ) {
        try {
            raiderIOService
                ?.getGearedCharacter(region, realm, name)
                ?.enqueue(object :
                    Callback<CharacterResponse> {
                    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                        throw  APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<CharacterResponse>,
                        response: Response<CharacterResponse>
                    ) {
                        if (response.isSuccessful) {
                            _gear.value = gearToGearItemList(response.body()!!.gear!!)
                        } else {
                            val error = NetworkErrorUtils.parseRIOError(response)
                            onFailure(call, APIError(error.message, error.statusCode, error.error))
                        }
                    }
                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }

    private fun gearToGearItemList(gear: Gear): List<GearItem> {
        val itemList = gear.items!!
        return listOfNotNull(
            itemList.head,
            itemList.neck,
            itemList.shoulder,
            itemList.back,
            itemList.chest,
            itemList.waist,
            itemList.wrist,
            itemList.hands,
            itemList.legs,
            itemList.feet,
            itemList.finger1,
            itemList.finger2,
            itemList.trinket1,
            itemList.trinket2,
            itemList.mainhand,
            itemList.offhand
        )
    }

    fun fetchItemInfo(gearItemList: List<GearItem>) {
        val tempList = mutableListOf<ItemInfoResponse>()
        gearItemList.forEach {
            val itemId = it.itemId!!
            blizzardService?.getItemInfo(itemId, BLIZZARD_ACCESS_TOKEN)
                ?.enqueue(object : Callback<ItemInfoResponse> {
                    override fun onFailure(call: Call<ItemInfoResponse>, t: Throwable) {
                        throw APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<ItemInfoResponse>,
                        response: Response<ItemInfoResponse>
                    ) {
                        if (response.isSuccessful) {
                            tempList.add(response.body()!!)
                            _items.value = tempList.toList()
                        } else {
                            val errorResponse = NetworkErrorUtils.parseBlizError(response)
                            onFailure(
                                call,
                                APIError(
                                    errorResponse.message,
                                    response.code(),
                                    errorResponse.error
                                )
                            )
                        }
                    }
                })
        }
    }

    fun fetchItemMedia(gearItemList: List<GearItem>) {
        val tempList = mutableListOf<BlizMediaResponse>()
        gearItemList.forEach {
            val itemId = it.itemId!!
            blizzardService
                ?.getItemMediaInfo(itemId, BLIZZARD_ACCESS_TOKEN)
                ?.enqueue(object : Callback<BlizMediaResponse> {
                    override fun onFailure(call: Call<BlizMediaResponse>, t: Throwable) {
                        throw APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<BlizMediaResponse>,
                        response: Response<BlizMediaResponse>
                    ) {
                        if (response.isSuccessful) {
                            tempList.add(response.body()!!)
                            _medias.value = tempList.toList()

                        } else {
                            val errorResponse = NetworkErrorUtils.parseBlizError(response)
                            onFailure(
                                call,
                                APIError(
                                    errorResponse.message,
                                    errorResponse.statusCode,
                                    errorResponse.error
                                )
                            )
                        }
                    }

                })
        }
    }

    fun fetchGemsMedia(gearItemList: List<GearItem>) {
        val tempList = mutableListOf<BlizMediaResponse>()
        gearItemList.forEach { it ->
            if (it.gems!!.isNotEmpty()) {
                it.gems!!.forEach {
                    blizzardService?.getItemMediaInfo(it, BLIZZARD_ACCESS_TOKEN)
                        ?.enqueue(object : Callback<BlizMediaResponse> {
                            override fun onFailure(call: Call<BlizMediaResponse>, t: Throwable) {
                                throw APIError(t.message)
                            }

                            override fun onResponse(
                                call: Call<BlizMediaResponse>,
                                response: Response<BlizMediaResponse>
                            ) {
                                if (response.isSuccessful) {
                                    tempList.add(response.body()!!)
                                    _gems.value = tempList.toList()
                                } else {
                                    val errorResponse = NetworkErrorUtils.parseBlizError(response)
                                    onFailure(
                                        call,
                                        APIError(
                                            errorResponse.message,
                                            errorResponse.statusCode,
                                            errorResponse.error
                                        )
                                    )
                                }
                            }
                        })
                }
            }
        }
    }
}
