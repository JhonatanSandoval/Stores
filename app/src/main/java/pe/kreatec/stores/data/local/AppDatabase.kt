package pe.kreatec.stores.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.kreatec.stores.data.local.dao.StoreDao
import pe.kreatec.stores.data.local.entity.StoreEntity

@Database(
    entities = [
        StoreEntity::class
    ],
    version = AppDatabase.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao

    companion object {
        const val DB_NAME = "Store_DB"
        const val DB_VERSION = 1
    }

}