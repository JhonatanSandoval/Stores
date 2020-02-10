package pe.kreatec.stores.inject

import pe.kreatec.stores.inject.components.AppComponent
import pe.kreatec.stores.inject.components.DaggerAppComponent
import pe.kreatec.stores.inject.modules.AppModule
import java.lang.IllegalArgumentException

object Injector {

    private var appComponent: AppComponent? = null

    @JvmStatic
    @Synchronized
    fun init(appModule: AppModule) {
        appComponent = DaggerAppComponent.builder()
            .appModule(appModule)
            .build()
    }

    @JvmStatic
    @Synchronized
    fun get(): AppComponent {
        appComponent?.let { return it }
        throw IllegalArgumentException("init() method must call first !")
    }

}