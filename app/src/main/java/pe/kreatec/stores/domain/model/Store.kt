package pe.kreatec.stores.domain.model

import pe.kreatec.stores.data.local.entity.StoreEntity

data class Store(
    val storeId: Int = 0,
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = "",
    val storeLogoUrl: String = "",
    val phoneNumber: String = ""
)

fun Store.transform(): StoreEntity =
    StoreEntity(storeId, name, latitude, longitude, address, storeLogoUrl, phoneNumber)