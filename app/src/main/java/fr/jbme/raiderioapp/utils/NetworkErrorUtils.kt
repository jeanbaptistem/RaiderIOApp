package fr.jbme.raiderioapp.utils

import org.json.JSONObject
import retrofit2.Response

object NetworkErrorUtils {
    fun parseRIOError(response: Response<*>): APIError {
        var statusCode: Int
        var error: String
        var message: String
        try {
            val errorBody: String? = response.errorBody()?.string()
            val bodyObj = JSONObject(errorBody.toString())
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
        var statusCode: Int
        var error: String
        var message: String
        try {
            val errorBody: String? = response.errorBody()?.string()
            val bodyObj = JSONObject(errorBody.toString())
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