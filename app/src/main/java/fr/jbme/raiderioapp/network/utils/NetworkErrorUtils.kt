package fr.jbme.raiderioapp.network.utils

import org.json.JSONObject
import retrofit2.Response

object NetworkErrorUtils {
    fun parseRIOError(response: Response<*>): APIError {
        val bodyObj: JSONObject
        var statusCode: Int
        var error: String
        var message: String
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
        return APIError(
            message,
            statusCode,
            error
        )
    }

    fun parseBlizError(response: Response<*>): APIError {
        val bodyObj: JSONObject
        var statusCode: Int
        var error: String
        var message: String
        try {
            val errorBody: String? = response.errorBody()?.string()
            bodyObj = JSONObject(errorBody!!)
            statusCode = bodyObj.getInt("code")
            error = bodyObj.getString("type")
            message = bodyObj.getString("detail")

        } catch (e: Exception) {
            e.printStackTrace()
            statusCode = -1
            error = "Parsing error"
            message = "Unable to parse error body"
        }
        return APIError(
            message,
            statusCode,
            error
        )
    }
}