package pe.kreatec.stores.data.remote.util

import java.io.IOException

suspend fun <T : Any> safeApiCall(
    call: suspend () -> ApiResponse<T>,
    errorMessage: String
): ApiResponse<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        ApiResponse.Exception(IOException(errorMessage, e))
    }
}
