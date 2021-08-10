package simple.program.data.repositories.activity

import android.util.Log
import kotlinx.coroutines.flow.Flow
import simple.program.data.api.services.VolunteerApi
import simple.program.data.db.cache.SessionManager
import simple.program.data.util.networkRequestWrapper
import simple.program.domain.model.ActivityFeed
import simple.program.domain.model.GenericData
import simple.program.domain.model.NewsFeed
import simple.program.domain.util.Resource
import javax.inject.Inject

class ActivityDataSourceImpl  @Inject constructor(
    private val api : VolunteerApi,
    private val sessionManager: SessionManager
)  : ActivityDataSource {

    override suspend fun getNewsFeeds(): Flow<Resource<List<NewsFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.newsFeed(
                    sessionManager.getSession().accessToken
                )
            },
            responseToDomain = {
                 it.toNewsFeeds()
            }
        )
    }

    override suspend fun getActivityFeeds(): Flow<Resource<List<ActivityFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.activityFeed(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it.toActivityFeeds()
            }
        )
    }

    override suspend fun getDonationFeeds(): Flow<Resource<List<ActivityFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.donationFeed(
                    sessionManager.getSession().accessToken
                )
            },
            responseToDomain = {
                it.toActivityFeeds(true)
            }
        )
    }

    override suspend fun getQuest(): Flow<Resource<List<ActivityFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.getUserQuest(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it.toActivityFeeds()
            }
        )
    }

    override suspend fun volunteerActivity(activityId: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.volunteer(
                    token = sessionManager.getSession().accessToken,
                    activityId = activityId,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun abandonQuest(uuid: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.abandonQuest(
                    token = sessionManager.getSession().accessToken,
                    uuid = uuid,
                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun donate(donationId: String, donation: String): Flow<Resource<GenericData>> {
        return networkRequestWrapper(
            fetch = {
                api.donate(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString(),
                    donationId = donationId,
                    donation = donation

                )
            },
            responseToDomain = {
                it
            }
        )
    }

    override suspend fun getQuestHistory(): Flow<Resource<List<ActivityFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.getQuestHistory(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it.toActivityFeeds()
            }
        )
    }

    override suspend fun getDonationHistory(): Flow<Resource<List<ActivityFeed>>> {
        return networkRequestWrapper(
            fetch = {
                api.getDonationHistory(
                    token = sessionManager.getSession().accessToken,
                    userId = sessionManager.getProfile().id.toString()
                )
            },
            responseToDomain = {
                it.toActivityFeeds(true)
            }
        )
    }
}