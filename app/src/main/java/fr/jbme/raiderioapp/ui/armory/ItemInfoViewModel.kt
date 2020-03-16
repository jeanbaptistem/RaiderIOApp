package fr.jbme.raiderioapp.ui.armory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.BLIZZARD_ACCESS_TOKEN
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemMediaResponse
import fr.jbme.raiderioapp.network.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.services.BlizzardService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemInfoViewModel : ViewModel() {

    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    private val _iconUrl = MutableLiveData<String>()
    val iconUrl: LiveData<String> = _iconUrl

    fun fetchItemIconUrl(itemId: Int) {
        var call: Call<ItemMediaResponse>? = null
        try {
            call = blizzardService?.getItemMediaInfo(itemId, BLIZZARD_ACCESS_TOKEN)
        } catch (e: Exception) {
            Log.i("BlizIconExcep", e.message!!)
        }
        call?.enqueue(object : Callback<ItemMediaResponse> {
            override fun onFailure(call: Call<ItemMediaResponse>, t: Throwable) {
                Log.i("RetrofitBlizIconExcep1", t.message!!)
            }

            override fun onResponse(
                call: Call<ItemMediaResponse>,
                response: Response<ItemMediaResponse>
            ) {
                if (response.isSuccessful) {
                    _iconUrl.value =
                        response.body()!!.assets.first { asset -> asset.key == "icon" }.value
                } else {
                    Log.i("RetrofitBlizIconExcep2", response.message())
                }
            }

        })
    }

    private val _itemInfo = MutableLiveData<ItemInfoResponse>()
    val itemInfo: LiveData<ItemInfoResponse> = _itemInfo

    fun fetchItemInfo(itemId: Int) {
        var call: Call<ItemInfoResponse>? = null
        try {
            call = blizzardService?.getItemInfo(itemId, BLIZZARD_ACCESS_TOKEN)
        } catch (e: Exception) {
            Log.i("BlizInfoExcep", e.message!!)
        }
        call?.enqueue(object : Callback<ItemInfoResponse> {
            override fun onFailure(call: Call<ItemInfoResponse>, t: Throwable) {
                Log.i("RetrofitBlizInfoExcep1", t.message!!)
            }

            override fun onResponse(
                call: Call<ItemInfoResponse>,
                response: Response<ItemInfoResponse>
            ) {
                if (response.isSuccessful) {
                    _itemInfo.value = response.body()!!
                } else {
                    Log.i("RetrofitBlizInfoExcep2", response.message())
                }
            }

        })
    }
}