package simple.program.data.api.services

import retrofit2.Response
import retrofit2.http.*
import simple.program.data.model.response.LoginResponse
import simple.program.data.model.response.FeedResponse
import simple.program.data.model.response.ShowProfileResponse
import simple.program.domain.model.AdsCheck
import simple.program.domain.model.GenericData


interface VolunteerApi {

    @POST("/api/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<LoginResponse>

    @POST("/api/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("fname") firstName : String,
        @Field("lname") lastName : String,
        @Field("email") email : String,
        @Field("min") min : String,
        @Field("password") password : String,
        @Field("c_password") confirmPassword : String,
    ) : Response<LoginResponse>


    @GET("/api/user")
    suspend fun showProfile(
        @Header("Authorization") token: String,
        @Query("email") email : String
    ) : Response<ShowProfileResponse>

    @GET("/api/ads")
    suspend fun checkAds(
        @Header("Authorization") token: String,
        @Query("user_id") userId : String
    ) : Response<AdsCheck>


    @GET("/api/user/feed")
    suspend fun newsFeed(
        @Header("Authorization") token: String,
    ) : Response<FeedResponse>

    @GET("/api/user/activity")
    suspend fun activityFeed(
        @Header("Authorization") token: String,
        @Query("user_id") userId: String
    ) : Response<FeedResponse>

    @GET("/api/user/activity/accepted")
    suspend fun getUserQuest(
        @Header("Authorization") token: String,
        @Query("user_id") userId: String
    ) : Response<FeedResponse>

    @GET("/api/user/donation")
    suspend fun donationFeed(
        @Header("Authorization") token: String,
    ) : Response<FeedResponse>

    @GET("/api/user/activity/{activityId}")
    suspend fun showActivity(
        @Header("Authorization") token: String,
        @Path("activityId") activityId: String
    ) : Response<FeedResponse>

    @POST("/api/create/activity")
    @FormUrlEncoded
    suspend fun volunteer(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String,
        @Field("activity_id") activityId: String,
        @Field("is_claim") isClaim: String = "0",
    ) : Response<GenericData>

    @POST("/api/claim/activity/points")
    @FormUrlEncoded
    suspend fun claimPoints(
        @Header("Authorization") token: String,
        @Field("uuid") uuid: String
    ) : Response<GenericData>

    @POST("/api/cash/in")
    @FormUrlEncoded
    suspend fun cashIn(
        @Header("Authorization") token: String,
        @Field("points") points: String,
        @Field("user_id") userId: String
    ) : Response<GenericData>

    @POST("api/store/points")
    @FormUrlEncoded
    suspend fun storePoints(
        @Header("Authorization") token: String,
        @Field("uuid") uuid: String,
        @Field("user_id") userId: String
    ) : Response<GenericData>

    @POST("/api/delete/activity")
    @FormUrlEncoded
    suspend fun abandonQuest(
        @Header("Authorization") token: String,
        @Field("uuid") uuid: String
    ) : Response<GenericData>


    @POST("/api/donate")
    @FormUrlEncoded
    suspend fun donate(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String,
        @Field("project_donation_id") donationId: String,
        @Field("donations") donation: String = "0",
    ) : Response<GenericData>

    @GET("/api/user/history/volunteer")
    suspend fun getQuestHistory(
        @Header("Authorization") token: String,
        @Query("user_id") userId: String
    ) : Response<FeedResponse>

    @GET("/api/user/history/donation")
    suspend fun getDonationHistory(
        @Header("Authorization") token: String,
        @Query("user_id") userId: String
    ) : Response<FeedResponse>


}