package pe.kreatec.stores.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.vikingsen.inject.worker.WorkerInject
import pe.kreatec.stores.domain.usecase.StoreUseCase

class SyncWorker
@WorkerInject constructor(
    @Assisted appContext: Context, @Assisted workerParams: WorkerParameters,
    private val storeUseCase: StoreUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        storeUseCase.getStoresFromNetwork(true)
        return Result.failure()
    }

    companion object {
        const val TAG = "SyncWorker"
    }
}