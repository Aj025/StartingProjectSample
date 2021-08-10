package simple.program.data.repositories.activity

import kotlinx.coroutines.flow.Flow
import simple.program.domain.model.ActivityFeed
import simple.program.domain.model.GenericData
import simple.program.domain.model.NewsFeed
import simple.program.domain.util.Resource

interface ActivityDataSource {
    suspend fun getNewsFeeds(): Flow<Resource<List<NewsFeed>>>
    suspend fun getActivityFeeds(): Flow<Resource<List<ActivityFeed>>>
    suspend fun getDonationFeeds(): Flow<Resource<List<ActivityFeed>>>
    suspend fun getQuest(): Flow<Resource<List<ActivityFeed>>>
    suspend fun volunteerActivity(activityId : String) : Flow<Resource<GenericData>>
    suspend fun abandonQuest(uuid : String) : Flow<Resource<GenericData>>
    suspend fun donate(donationId : String, donation : String) : Flow<Resource<GenericData>>
    suspend fun getQuestHistory() : Flow<Resource<List<ActivityFeed>>>
    suspend fun getDonationHistory() : Flow<Resource<List<ActivityFeed>>>
}