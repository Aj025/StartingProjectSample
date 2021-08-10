package simple.program.data.util

import retrofit2.Response
import simple.program.domain.util.Resource


class GenericRetrofitErrorHandler : RetrofitErrorHandler {
    override fun <T, R> processError(response: Response<T>): Resource<R> {
        when (response.code()) {
            else -> {
                return Resource.Error(response.message())
            }
        }
    }
}
