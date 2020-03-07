package fr.jbme.raiderioapp.network

import fr.jbme.raiderioapp.data.model.APIError
import org.json.JSONObject
import retrofit2.Response

object NetworkErrorUtils {
    fun parseError(response: Response<*>): APIError {
        val bodyObj: JSONObject?
        var statusCode: Int?
        var error: String?
        var message: String?
        try {
            val errorBody: String? = response.errorBody()?.string()
            bodyObj = JSONObject(errorBody!!)
            statusCode = bodyObj.getInt("statusCode")
            error = bodyObj.getString("error")
            message = bodyObj.getString("message")

        } catch (e: Exception) {
            e.printStackTrace()
            statusCode = -1
            error = "Parsing error"
            message = "Unable to parse error body"
        }
        return APIError(statusCode, error, message)
    }
}