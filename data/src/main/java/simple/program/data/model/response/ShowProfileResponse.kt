package simple.program.data.model.response

import simple.program.domain.model.Profile

data class ShowProfileResponse(
    val `data`: List<Data>
) {
    data class Data(
        val current_points: Int,
        val email: String,
        val fname: String,
        val id: Int,
        val lname: String,
        val min: String,
        val picture: String,
        val rank: String,
        val tatal_point: Int?,
        val total_donation: Int?
    )

    fun toDomainModel() : Profile {
        if (data.isNotEmpty()){
            data[0].apply {
                return Profile(
                    email = email,
                    fName = fname,
                    lName = lname,
                    min = min,
                    id = id,
                    rank = rank,
                    picture = picture,
                    points = current_points,
                    totalPoints = tatal_point ?: 0,
                    totalDonations = total_donation ?: 0
                )
            }
        }
        else {
            return Profile()
        }
    }
}