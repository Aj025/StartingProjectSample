package simple.program.domain.repositories

import kotlinx.coroutines.flow.Flow
import simple.program.domain.model.AdsCheck
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.util.Resource

interface AccountRepository {
    suspend fun getRemoteProfile() : Flow<Resource<Profile>>
    suspend fun cashIn( points : String) : Flow<Resource<GenericData>>
    suspend fun claimPoints(uuid : String) : Flow<Resource<GenericData>>
    suspend fun checkAds() : Flow<Resource<AdsCheck>>
    suspend fun storePoints(uuid : String) : Flow<Resource<GenericData>>
}