package simple.program.domain.model

import java.io.Serializable

data class Profile(
        val points: Int = 0,
        val email: String = "",
        val fName: String = "",
        val id: Int = 0,
        val lName: String= "",
        val min: String = "",
        val picture: String = "",
        val rank: String = "",
        val totalPoints: Int = 0,
        val totalDonations: Int = 0
    ): Serializable
