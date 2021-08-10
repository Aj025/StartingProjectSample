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
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.db.cache.SessionManagerImpl
import simple.program.data.repositories.account.AccountDataSource
import simple.program.data.repositories.account.AccountDataSourceImpl
import simple.program.data.repositories.account.AccountRepositoryImpl
import simple.program.data.repositories.activity.ActivityDataSource
import simple.program.data.repositories.activity.ActivityDataSourceImpl
import simple.program.data.repositories.activity.ActivityRepositoryImpl
import simple.program.data.repositories.login.LoginDataSource
import simple.program.data.repositories.login.LoginDataSourceImpl
import simple.program.data.repositories.login.LoginRepositoryImpl
import simple.program.data.repositories.register.RegisterDataSource
import simple.program.data.repositories.register.RegisterDataSourceImpl
import simple.program.data.repositories.register.RegisterRepositoryImpl
import simple.program.data.util.Constant
import simple.program.data.util.Constant.SESSION_PREF
import simple.program.domain.repositories.AccountRepository
import simple.program.domain.repositories.ActivityRepository
import simple.program.domain.repositories.LoginRepository
import simple.program.domain.repositories.RegisterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAccountRepository(
        dataSource: AccountDataSource
    ): AccountRepository {
        return AccountRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        dataSource: LoginDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(
        dataSource: RegisterDataSource
    ): RegisterRepository {
        return RegisterRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideActivityRepository(
        dataSource : ActivityDataSource
    ): ActivityRepository {
        return ActivityRepositoryImpl(dataSource)
    }

    @Provides
    fun provideRegisterDataSource(
        api : VolunteerApi,
        sessionManager: SessionManager
    ): RegisterDataSource {
        return RegisterDataSourceImpl(api, sessionManager)
    }

    @Provides
    fun provideActivityDataSource(
        api : VolunteerApi,
        sessionManager: SessionManager
    ): ActivityDataSource {
        return ActivityDataSourceImpl(api, sessionManager)
    }

    @Provides
    fun provideAccountDataSource(
        api : VolunteerApi,
        sessionManager: SessionManager
    ): AccountDataSource {
        return AccountDataSourceImpl(api, sessionManager)
    }

    @Provides
    fun provideLoginDataSource(
        api : VolunteerApi,
        sessionManager: SessionManager
    ): LoginDataSource {
        return LoginDataSourceImpl(api, sessionManager)
    }
}