package pe.kreatec.stores.data.remote

import androidx.annotation.WorkerThread
import pe.kreatec.stores.data.remote.response.StoreListResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @WorkerThread
    @GET("stores.json")
    suspend fun getStores(): Response<StoreListResponse>

}
