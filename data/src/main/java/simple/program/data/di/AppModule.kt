package simple.program.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import simple.program.data.BuildConfig
import simple.program.data.api.services.CoinGeckoApi
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.db.cache.SessionManagerImpl
import simple.program.data.repositories.account.AccountDataSource
import simple.program.data.repositories.account.AccountDataSourceImpl
import simple.program.data.repositories.account.AccountRepositoryImpl
import simple.program.data.repositories.activity.ActivityDataSource
import simple.program.data.repositories.activity.ActivityDataSourceImpl
import simple.program.data.repositories.login.LoginDataSource
import simple.program.data.repositories.login.LoginDataSourceImpl
import simple.program.data.repositories.login.LoginRepositoryImpl
import simple.program.data.util.Constant
import simple.program.data.util.Constant.SESSION_PREF
import simple.program.domain.repositories.AccountRepository
import simple.program.domain.repositories.LoginRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_URL2)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(VolunteerApi::class.java)

    @Provides
    @Singleton
    fun provideCoinGeckoApiService(retrofit: Retrofit) = retrofit.create(CoinGeckoApi::class.java)



    @Provides
    @Singleton
    fun provideSharedPreference(
        @ApplicationContext appContext: Context,
    ): SharedPreferences {
        return appContext.getSharedPreferences(SESSION_PREF, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSessionManager(
        sharedPreferences: SharedPreferences
    ): SessionManager =
        SessionManagerImpl(
            sharedPreferences
        )
}