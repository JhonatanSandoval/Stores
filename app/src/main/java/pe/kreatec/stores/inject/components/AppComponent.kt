package pe.kreatec.stores.inject.components

import android.app.Application
import dagger.Component
import pe.kreatec.stores.App
import pe.kreatec.stores.inject.modules.AppModule
import pe.kreatec.stores.inject.modules.NetworkModule
import pe.kreatec.stores.presentation.ux.details.StoreDetailsFragment
import pe.kreatec.stores.presentation.ux.stores.StoresFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(fragment: StoresFragment)
    fun inject(fragment: StoreDetailsFragment)

    fun application(): Application
}