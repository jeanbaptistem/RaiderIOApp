package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.ViewModel

class RaidViewModel : ViewModel() {
/*
    private var _raid = MutableLiveData<List<Raids>>()
    val raid: LiveData<List<Raids>> = _raid


    private var blizzardWebService: BlizzardWebService? =
        RetrofitBlizzardWebInstance.retrofitInstance?.create(
            BlizzardWebService::class.java
        )

    fun fetchRaidData(region: String?, realm: String?, name: String?) {
        try {
            blizzardWebService
                ?.getCharacterInfo(region!!, realm!!, name!!)
                ?.enqueue(object :
                    Callback<BlizCharacterResponse> {
                    override fun onFailure(call: Call<BlizCharacterResponse>, t: Throwable) {
                        throw  APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<BlizCharacterResponse>,
                        response: Response<BlizCharacterResponse>
                    ) {
                        if (response.isSuccessful) {
                            _raid.value = response.body()?.character?.pve?.raids
                        } else {
                            val error = NetworkErrorUtils.parseRIOError(response)
                            onFailure(call, error)
                        }
                    }
                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }
*/
}