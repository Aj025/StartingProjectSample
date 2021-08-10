package simple.program.domain.model

import java.io.Serializable


data class NotificationItem(
    var id: String,
    val body: String,
    val created_at: String,
    var is_read: Int,
    val min: String,
    val notification_id: Int,
    val image_url: String?,
    val subject: String,
    val title: String,
    val type: Any?,
    val user_id: Int,
    val url_link: String? = ""
) : Serializable
