package fr.jbme.raiderioapp.data.model.utils

class APIError(
    override var message: String?,
    var statusCode: Int? = null,
    var error: String? = null
) : Exception(message)
