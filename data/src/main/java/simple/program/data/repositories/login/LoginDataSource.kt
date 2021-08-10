package simple.program.data.repositories.login

import kotlinx.coroutines.flow.Flow
import simple.program.data.model.response.ShowProfileResponse
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.util.Resource

interface LoginDataSource {
    suspend fun login(email : String , password : String): Flow<Resource<GenericData>>
}