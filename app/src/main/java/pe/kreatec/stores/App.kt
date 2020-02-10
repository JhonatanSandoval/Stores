package pe.kreatec.stores

import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import com.vikingsen.inject.worker.WorkerFactory
import pe.kreatec.stores.inject.Injector
import pe.kreatec.stores.inject.modules.AppModule
import timber.log.Timber
import javax.inject.Inject

class App : MultiDexApplication(), Configuration.Provider {

    @Inject lateinit var workerFactory: WorkerFactory

    init {
        Injector.init(AppModule(this))
    }

    override fun onCreate() {
        super.onCreate()

        // initialize dagger
        Injector.get().inject(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}