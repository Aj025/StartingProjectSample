package simple.program.data.api.services

import retrofit2.Response
import retrofit2.http.*
import simple.program.data.model.response.LoginResponse
import simple.program.data.model.response.FeedResponse
import simple.program.data.model.response.GetCoinDetailsResponse
import simple.program.data.model.response.ShowProfileResponse
import simple.program.domain.model.AdsCheck
import simple.program.domain.model.GenericData


interface CoinGeckoApi {

    @GET("api/v3/coins/{coinId}")
    suspend fun showProfile(
        @Path("coinId") coinId: String
    ) : Response<GetCoinDetailsResponse>

}