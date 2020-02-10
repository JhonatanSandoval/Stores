package pe.kreatec.stores.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.vikingsen.inject.worker.WorkerInject

class SyncWorker
@WorkerInject constructor(
    @Assisted appContext: Context, @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return Result.failure()
    }
}