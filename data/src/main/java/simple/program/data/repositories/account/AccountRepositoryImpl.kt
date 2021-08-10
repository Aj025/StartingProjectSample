package simple.program.data.repositories.account

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
import simple.program.domain.util.Resource
import javax.inject.Inject

class AccountRepositoryImpl  @Inject constructor(
    private val dataSource: AccountDataSource
) : AccountRepository {

    override suspend fun getRemoteProfile(): Flow<Resource<Profile>> {
        return dataSource.getProfile()
    }
    override suspend fun cashIn(points: String) = dataSource.cashIn(points)
    override suspend fun claimPoints(uuid: String) = dataSource.claimPoints(uuid)
    override suspend fun checkAds()= dataSource.checkAds()

    override suspend fun storePoints(uuid: String)= dataSource.storePoints(uuid)
}
