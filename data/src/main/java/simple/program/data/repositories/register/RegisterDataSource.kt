package simple.program.data.repositories.register

import kotlinx.coroutines.flow.Flow
import simple.program.data.model.response.ShowProfileResponse
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.util.Resource

interface RegisterDataSource {
    suspend fun register(
        mobileNumber : String,
        firstName : String,
        lastName : String,
        password : String,
        confirmPassword : String,
        email : String
    ): Flow<Resource<GenericData>>
}