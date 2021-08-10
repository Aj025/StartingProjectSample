package simple.program.domain.repositories

import kotlinx.coroutines.flow.Flow
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.model.UserSession
import simple.program.domain.util.Resource

interface RegisterRepository {
    suspend fun register(
        mobileNumber : String,
        firstName : String,
        lastName : String,
        password : String,
        confirmPassword : String,
        email : String
    ) : Flow<Resource<GenericData>>
}