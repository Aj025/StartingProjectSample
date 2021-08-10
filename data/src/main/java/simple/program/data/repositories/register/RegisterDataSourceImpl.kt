package simple.program.data.repositories.register

import dagger.Provides
import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.util.networkRequestWrapper
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.model.UserSession
import simple.program.domain.util.Resource
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val api: VolunteerApi,
    private val sessionManager: SessionManager
) : RegisterDataSource {

    override suspend fun register(
        mobileNumber: String,
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String,
        email: String
    ): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.register(
                    min = mobileNumber,
                    firstName = firstName,
                    lastName = lastName,
                    password = password,
                    confirmPassword = confirmPassword,
                    email = email
                )
            },
            responseToDomain = {
                GenericData("success")
            },
            onSuccess = {
                sessionManager.save(
                    UserSession(
                        accessToken = it.access_token,
                        expiresIn = it.expires_in,
                        refreshToken = it.refresh_token,
                        tokenType = it.token_type
                    )
                )
                sessionManager.saveEmail(email)
            }
        )
    }

}