package simple.program.data.repositories.login

import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.util.networkRequestWrapper
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.model.UserSession
import simple.program.domain.util.Resource
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val api: VolunteerApi,
    private val sessionManager: SessionManager
) : LoginDataSource {

    override suspend fun login(email: String, password: String): Flow<Resource<GenericData>> {
            return networkRequestWrapper(
            fetch = {
                api.login(
                    email = email,
                    password = password
                )
            },
            responseToDomain = {
                GenericData("success")
            },
            onSuccess = {
                sessionManager.save(
                    UserSession(
                        accessToken = it.access_token,
                        expiresIn =  it.expires_in,
                        refreshToken = it.refresh_token,
                        tokenType = it.token_type
                    )
                )
                sessionManager.saveEmail(email)
            }
        )
    }

}