package simple.program.domain.model

import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class ActivityFeed(
    val id: Int? = null,
    val uuid: String? = null,
    val category: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val link: String? = null,
    val picture: String? = null,
    val subtitle: String? = null,
    val title: String? = null,
    val contact_person: String? = null,
    val date: String? = null,
    val email: String? = null,
    val get_points: Int? = null,
    val location: Any? = null,
    val mobile_number: String? = null,
    val target: Int? = null,
    val time: String? = null,
    val volunteer: Int? = null,
    val total_danation: Int? = null,
    val donated : String
) : Serializable
