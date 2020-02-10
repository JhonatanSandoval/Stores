package pe.kreatec.stores.data.remote.response

import pe.kreatec.stores.domain.model.Store

data class StoreListResponse(
    val stores: List<StoreResponse>?
)

data class StoreResponse(
    val storeId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val storeLogoUrl: String,
    val phoneNumber: String
)

fun StoreResponse.transform(): Store =
    Store(storeId, name, latitude, longitude, address, storeLogoUrl, phoneNumber)

fun List<StoreResponse>.transformList(): List<Store> = map { it.transform() }