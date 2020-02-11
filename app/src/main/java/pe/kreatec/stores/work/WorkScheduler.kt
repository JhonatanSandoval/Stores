package pe.kreatec.stores.work

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkScheduler
@Inject constructor(
    private val context: Context
) {

    private val workManager by lazy { WorkManager.getInstance(context) }
    private val networkConstraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    fun scheduleSyncWorkerRepeating() {
        Timber.i("scheduling sync worker ... ")

        val workRequest = createStandardPeriodicWorkRequest<SyncWorker>(PERIODIC_TIME, PERIODIC_TIME_UNIT)
        workManager.apply {
            enqueueUniquePeriodicWork(SyncWorker.TAG, ExistingPeriodicWorkPolicy.KEEP, workRequest)
        }
    }

    private inline fun <reified T : CoroutineWorker> createStandardPeriodicWorkRequest(repeatInterval: Long, timeUnit: TimeUnit, inputDataBuilder: Data.Builder = Data.Builder()): PeriodicWorkRequest {
        inputDataBuilder.putBoolean(KEY_PERIODIC_CHECK, true)

        val workRequestBuilder = PeriodicWorkRequestBuilder<T>(repeatInterval, timeUnit, 30, TimeUnit.MINUTES) // the repeating could execute in the next 0-30min

        workRequestBuilder.setConstraints(networkConstraint)
        workRequestBuilder.setInputData(inputDataBuilder.build())

        return workRequestBuilder.build()
    }

    companion object {
        private const val KEY_PERIODIC_CHECK = "KEY_PERIODIC_CHECK"

        private const val PERIODIC_TIME = 1.toLong()
        private val PERIODIC_TIME_UNIT = TimeUnit.HOURS

    }
}