package pe.kreatec.stores.domain.model

data class Store(
    val storeId: Int = 0,
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = "",
    val storeLogoUrl: String = "",
    val phoneNumber: String = ""
)