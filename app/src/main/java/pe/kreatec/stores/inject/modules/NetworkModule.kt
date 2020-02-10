package pe.kreatec.stores.inject.modules

import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pe.kreatec.stores.BuildConfig
import pe.kreatec.stores.data.remote.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(converterFactory)
            baseUrl(BuildConfig.BASE_URL)
        }.build()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        LoggingInterceptor.Builder().apply {
            loggable(BuildConfig.DEBUG)
            request("Request")
            response("Response")
            addHeader("Accept", "application/json")
            addHeader("client-app-version", BuildConfig.VERSION_NAME)
        }.build()

}