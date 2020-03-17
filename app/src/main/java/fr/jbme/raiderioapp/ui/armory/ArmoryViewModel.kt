package fr.jbme.raiderioapp.ui.armory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.character.Gear
import fr.jbme.raiderioapp.data.model.character.GearItem
import fr.jbme.raiderioapp.network.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.network.utils.APIError
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback1

class ArmoryViewModel : ViewModel() {

    private val _gear = MutableLiveData<List<GearItem>>()
    val gear: LiveData<List<GearItem>> = _gear

    private var raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchCharacterData(
        region: String,
        realm: String,
        name: String,
        errorCallback: (e: APIError) -> Unit
    ) {
        var call: Call<CharacterResponse>? = null
        try {
            call = raiderIOService?.getGearedCharacter(region, realm, name)
        } catch (e: Exception) {
            errorCallback(APIError(e.message))
        }
        call?.enqueue(object : Callback1<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                errorCallback(APIError(t.message))
            }

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _gear.value = gearToGearItemList(response.body()!!.gear!!)
                } else {
                    val error = NetworkErrorUtils.parseError(response)
                    onFailure(call, APIError(error.message, error.statusCode, error.error))
                }
            }

        })
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
}
