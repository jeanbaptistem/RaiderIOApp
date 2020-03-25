package fr.jbme.raiderioapp.utils.network

class APIError(
    override var message: String?,
    var statusCode: Int? = null,
    var error: String? = null
) : Exception(message)
