package simple.program.domain.repositories

import kotlinx.coroutines.flow.Flow
import simple.program.domain.model.GenericData
import simple.program.domain.model.Profile
import simple.program.domain.util.Resource

interface LoginRepository {
    suspend fun login(email : String , password : String) : Flow<Resource<GenericData>>
}