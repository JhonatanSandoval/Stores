package pe.kreatec.stores.inject.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import pe.kreatec.stores.data.local.AppDatabase
import javax.inject.Singleton

@Module(includes = [AssistModule::class, VMModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideGlobalContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}