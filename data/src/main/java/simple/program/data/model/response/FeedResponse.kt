package simple.program.data.model.response

import simple.program.domain.model.ActivityFeed
import simple.program.domain.model.NewsFeed

data class FeedResponse(
    val `data`: List<Data>,
    val meta: Meta? = null
) {

    data class Pagination(
        val count: Int? = null,
        val current_page: Int? = null,
        val links: Links? = null,
        val per_page: Int? = null,
        val total: Int? = null,
        val total_pages: Int? = null
    )

    data class Meta(
        val pagination: Pagination? = null
    )

    class Links()


    data class Data(
        val activity_details: ActivityDetails? = null,
        val category: String? = null,
        val created_at: String? = null,
        val description: String? = null,
        val id: Int? = null,
        val uuid: String? = null,
        val link: String? = null,
        val picture: String? = null,
        val subtitle: String? = null,
        val title: String? = null,
        val donated : String? = null
    )

    data class ActivityDetails(
        val contact_person: String? = null,
        val date: String? = null,
        val email: String? = null,
        val get_points: Int? = null,
        val location: Any? = null,
        val mobile_number: String? = null,
        val target: Int? = null,
        val time: String? = null,
        val volunteer: Int? = null,
        val total_danation: Int? = null
    )


    fun toActivityFeeds(isDonation : Boolean = false): List<ActivityFeed> {
        val list = arrayListOf<ActivityFeed>()
        for (d in data) {
            list.add(
                ActivityFeed(
                    id = d.id,
                    uuid = d.uuid,
                    category = if (isDonation) "donation" else d.category,
                    created_at = d.created_at,
                    description = d.description,
                    link = d.link,
                    picture = d.picture,
                    subtitle = d.subtitle,
                    title = d.title,
                    contact_person = d.activity_details?.contact_person ,
                    date = d.activity_details?.date,
                    email = d.activity_details?.email,
                    get_points = d.activity_details?.get_points,
                    location = d.activity_details?.location,
                    mobile_number = d.activity_details?.mobile_number,
                    target = d.activity_details?.target,
                    time = d.activity_details?.time,
                    volunteer = d.activity_details?.volunteer,
                    total_danation = d.activity_details?.total_danation,
                    donated = d.donated ?: ""
                )
            )
        }
        return list
    }


    fun toNewsFeeds(): List<NewsFeed> {
        val list = arrayListOf<NewsFeed>()
        for (d in data) {
            list.add(
                NewsFeed(
                    id = d.id.toString(),
                    title = d.title ?: "",
                    image_url = d.picture ?: "",
                    url = d.link ?: ""
                )
            )
        }
        return list
    }
}