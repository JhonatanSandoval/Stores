package pe.kreatec.stores.inject.modules

import dagger.Module
import dagger.Provides
import pe.kreatec.stores.data.local.AppDatabase
import pe.kreatec.stores.data.remote.Api
import pe.kreatec.stores.data.repository.StoreDataRepository
import pe.kreatec.stores.domain.repository.StoreRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStoreRepository(api: Api, appDatabase: AppDatabase): StoreRepository = StoreDataRepository(api, appDatabase)

}