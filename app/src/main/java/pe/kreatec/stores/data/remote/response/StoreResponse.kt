package pe.kreatec.stores.data.remote.response

import pe.kreatec.stores.data.local.entity.StoreEntity
import pe.kreatec.stores.domain.model.Store

data class StoreListResponse(
    val stores: List<StoreResponse>?
)

data class StoreResponse(
    val storeId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String? = "",
    val storeLogoURL: String? = "",
    val phone: String? = "",
    val city: String? = ""
)

fun StoreResponse.transform(): Store =
    Store(storeId, name, latitude, longitude, address, storeLogoURL, phone, city)

fun List<StoreResponse>.transformList(): List<Store> = map { it.transform() }

fun StoreResponse.transformToEntity(): StoreEntity =
    StoreEntity(storeId, name, latitude, longitude, address, storeLogoURL, phone, city)

fun List<StoreResponse>.transform(): List<StoreEntity> = map { it.transformToEntity() }