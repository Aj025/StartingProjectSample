package simple.program.data.repositories.account

import android.util.Log
import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.util.networkRequestWrapper
import simple.program.domain.model.AdsCheck
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.util.Resource
import javax.inject.Inject

class AccountDataSourceImpl @Inject constructor(
    private val api: VolunteerApi,
    private val sessionManager: SessionManager
) : AccountDataSource {


    // TODO Call api on the login
    override suspend fun getProfile(): Flow<Resource<Profile>> {
        return networkRequestWrapper(
            fetch = {
                api.showProfile(
                    token = sessionManager.getSession().accessToken,
                    sessionManager.getEmail()
                )
            },
            responseToDomain = {
                it.toDomainModel()
            },
            onSuccess = {
                Log.d("Test", it.toString())
                sessionManager.saveProfile(it.toDomainModel())
            }
        )
    }

    override suspend fun cashIn(points: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.cashIn(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString(),
                    points = points
                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun claimPoints(uuid: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.claimPoints(
                    token = sessionManager.getSession().accessToken,
                    uuid = uuid
                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun checkAds(): Flow<Resource<AdsCheck>> {
        return networkRequestWrapper(
            fetch = {
                api.checkAds(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun storePoints(uuid: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.storePoints(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString(),
                    uuid = uuid
                )
            },
            responseToDomain = {
                it
            }
        )
    }
}