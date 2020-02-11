package pe.kreatec.stores.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.kreatec.stores.data.remote.util.ApiResponse
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.domain.repository.StoreRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreUseCase
@Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend fun getStoresFromNetwork(save: Boolean): ApiResponse<List<Store>?> = storeRepository.getStoresFromNetwork(save)

    suspend fun getStoresFromDb(): Flow<List<Store>> = storeRepository.getStoreFromDb()

}