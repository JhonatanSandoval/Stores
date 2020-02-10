package pe.kreatec.stores.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.kreatec.stores.data.local.entity.StoreEntity

@Dao
interface StoreDao {

    @Query("SELECT  * FROM ${StoreEntity.TABLE_NAME}")
    fun get(): Flow<List<StoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(store: StoreEntity)

    @Update
    fun update(store: StoreEntity)

    @Delete
    fun delete(store: StoreEntity)

}