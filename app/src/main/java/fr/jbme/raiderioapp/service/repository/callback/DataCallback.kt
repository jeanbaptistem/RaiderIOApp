package fr.jbme.raiderioapp.service.repository.callback

import fr.jbme.raiderioapp.utils.network.Result

interface DataCallback {
    fun onDataLoaded(result: Result.Success<*>)

    fun onDataNotAvailable(error: Result.Error)
}


