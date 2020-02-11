package pe.kreatec.stores.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pe.kreatec.stores.domain.model.Store

@Entity(tableName = StoreEntity.TABLE_NAME)
data class StoreEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val storeId: Int = 0,

    @ColumnInfo
    val name: String = "",

    @ColumnInfo
    val latitude: Double = 0.0,

    @ColumnInfo
    val longitude: Double = 0.0,

    @ColumnInfo
    val address: String = "",

    @ColumnInfo
    val storeLogoUrl: String = "",

    @ColumnInfo
    val phoneNumber: String = ""

) {
    companion object {
        const val TABLE_NAME = "stores"
    }
}

fun StoreEntity.transform(): Store =
    Store(storeId, name, latitude, longitude, address, storeLogoUrl, phoneNumber)

fun List<StoreEntity>.transformList(): List<Store> = map { it.transform() }
