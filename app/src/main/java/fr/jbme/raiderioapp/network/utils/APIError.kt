package fr.jbme.raiderioapp.network.utils

class APIError(
    override var message: String?,
    var statusCode: Int? = null,
    var error: String? = null
) : Exception(message)
