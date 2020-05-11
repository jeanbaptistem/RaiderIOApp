package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.service.model.wowprogress.WoWProgressResponse
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitWoWProgressInstance
import fr.jbme.raiderioapp.service.network.service.WoWProgressService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchRepository {
    private val wowProgressService: WoWProgressService? =
        RetrofitWoWProgressInstance.retrofitInstance?.create(
            WoWProgressService::class.java
        )

    fun searchCharacters(query: String, callback: DataCallback) {
        wowProgressService?.searchAutoComplete(query)
            ?.enqueue(object : Callback<WoWProgressResponse> {
                override fun onFailure(call: Call<WoWProgressResponse>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<WoWProgressResponse>,
                    response: Response<WoWProgressResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!.characters))
                    } else {
                        callback.onDataNotAvailable(Result.Error(Exception("Error with wowProgressService")))
                    }
                }
            })
    }
}
