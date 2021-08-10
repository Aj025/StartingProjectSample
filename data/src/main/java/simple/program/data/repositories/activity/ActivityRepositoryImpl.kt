package simple.program.data.repositories.activity

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
import simple.program.domain.repositories.ActivityRepository
import simple.program.domain.util.Resource
import javax.inject.Inject

class ActivityRepositoryImpl  @Inject constructor(
    private val dataSource: ActivityDataSource
) : ActivityRepository {

    override suspend fun getRemoteNews() =
       dataSource.getNewsFeeds()

    override suspend fun getRemoteActivity() =  dataSource.getActivityFeeds()
    override suspend fun getRemoteDonation() = dataSource.getDonationFeeds()
    override suspend fun volunteerActivity(activityId: String) = dataSource.volunteerActivity(activityId)
    override suspend fun getQuest() = dataSource.getQuest()
    override suspend fun abandonQuest(uuid: String) = dataSource.abandonQuest(uuid)
    override suspend fun donate(donationId: String, donation: String) = dataSource.donate(donationId , donation)
    override suspend fun getQuestHistory(): Flow<Resource<List<ActivityFeed>>>  = dataSource.getQuestHistory()

    override suspend fun getDonationHistory(): Flow<Resource<List<ActivityFeed>>>  = dataSource.getDonationHistory()

}