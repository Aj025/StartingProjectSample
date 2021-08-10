package simple.program.domain.model

data class UserSession(
    val accessToken: String = "",
    val expiresIn: Int = 0,
    val refreshToken: String = "",
    val tokenType: String = "Bearer"
)