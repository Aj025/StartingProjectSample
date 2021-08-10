package simple.program.data.repositories.register

import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.model.response.FeedResponse
import simple.program.data.repositories.activity.ActivityDataSource
import simple.program.data.util.GenericRetrofitErrorHandler
import simple.program.data.util.handleRetrofitWithErrorHandler
import simple.program.domain.model.*
import simple.program.domain.repositories.AccountRepository
import simple.program.domain.repositories.ActivityRepository
import simple.program.domain.repositories.LoginRepository
import simple.program.domain.repositories.RegisterRepository
import simple.program.domain.util.Resource

class RegisterRepositoryImpl (
    private val dataSource: RegisterDataSource
) : RegisterRepository {

    override suspend fun register(
        mobileNumber: String,
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String,
        email: String
    ) = dataSource.register(
        mobileNumber = mobileNumber,
        firstName = firstName,
        lastName = lastName,
        password = password,
        confirmPassword = confirmPassword,
        email = email
    )
}