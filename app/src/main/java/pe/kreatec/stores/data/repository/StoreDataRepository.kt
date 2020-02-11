package pe.kreatec.stores.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import pe.kreatec.stores.data.local.AppDatabase
import pe.kreatec.stores.data.local.entity.StoreEntity
import pe.kreatec.stores.data.local.entity.transformList
import pe.kreatec.stores.data.remote.Api
import pe.kreatec.stores.data.remote.response.transform
import pe.kreatec.stores.data.remote.response.transformToEntity
import pe.kreatec.stores.data.remote.util.ApiResponse
import pe.kreatec.stores.data.remote.util.safeApiCall
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.domain.repository.StoreRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreDataRepository
@Inject constructor(
    private val api: Api,
    private val appDatabase: AppDatabase
) : StoreRepository {

    private val storeDao by lazy { appDatabase.storeDao() }

    override suspend fun getStoresFromNetwork(save: Boolean): ApiResponse<List<Store>?> {
        return safeApiCall(
            call = {
                val result = api.getStores()
                if (result.isSuccessful) {
                    result.body()?.stores?.let { stores ->
                        if (save) saveStoreToDb(stores.map { it.transformToEntity() })
                        return@safeApiCall ApiResponse.Success(stores.map { it.transform() })
                    }
                }
                ApiResponse.Error(result.code(), result.message())
            },
            errorMessage = "Network error happened !"
        )
    }

    private fun saveStoreToDb(stores: List<StoreEntity>) {
        stores.forEach { storeDao.insert(it) }
    }

    override suspend fun getStoreFromDb(): Flow<List<Store>> = flow {
        storeDao.get().collect { it.transformList().asFlow() }
    }
}