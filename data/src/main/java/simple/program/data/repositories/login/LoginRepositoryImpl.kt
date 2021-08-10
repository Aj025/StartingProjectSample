package simple.program.data.repositories.login

import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.model.response.FeedResponse
import simple.program.data.repositories.activity.ActivityDataSource
import simple.program.data.util.GenericRetrofitErrorHandler
import simple.program.data.util.handleRetrofitWithErrorHandler
import simple.program.domain.model.ActivityFeed
import simple.program.domain.model.GenericData
import simple.program.domain.model.NewsFeed
import simple.program.domain.model.Profile
import simple.program.domain.repositories.AccountRepository
import simple.program.domain.repositories.ActivityRepository
import simple.program.domain.repositories.LoginRepository
import simple.program.domain.util.Resource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {

    override suspend fun login(email: String, password: String) =
        dataSource.login(email = email, password = password)
}