package pe.kreatec.stores.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.kreatec.stores.data.remote.util.ApiResponse
import pe.kreatec.stores.domain.model.Store

interface StoreRepository {

    suspend fun getStoresFromNetwork(): ApiResponse<List<Store>?>

    suspend fun getStoreFromDb(): Flow<List<Store>>

    suspend fun deleteStores()

    suspend fun saveStore(store: Store)

}