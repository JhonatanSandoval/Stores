package pe.kreatec.stores.work

import android.content.Context
import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkScheduler
@Inject constructor(
    private val context: Context
) {

    private val workManger by lazy { WorkManager.getInstance(context) }

    fun scheduleSyncWorkerRepeating() {

    }

}