package simple.program.data.util

import retrofit2.Response
import simple.program.domain.util.Resource

interface RetrofitErrorHandler {
    fun <T, R>processError(response: Response<T>) : Resource<R>
}
