package simple.program.data.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import simple.program.domain.util.Resource


const val DEFAULT_ERROR_MESSAGE = "Oops, that made the busy bees a bit dizzy. Please try again!"

inline fun <ResultType, RequestType> networkRequestWrapper(
    crossinline fetch: suspend () -> Response<RequestType>,
    crossinline responseToDomain: (RequestType) -> ResultType,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline onSuccess: (RequestType) -> Unit = { Unit },
    errorHandler: RetrofitErrorHandler = GenericRetrofitErrorHandler()
) = flow<Resource<ResultType>> {
    emit(Resource.Loading())

    try {
        val response = fetch()
        if (response.isSuccessful) {
            Log.d("Test", response.code().toString())
            response.body()?.let {
                onSuccess(it)
                emit(Resource.Success(responseToDomain(it)))
            } ?: throw NullPointerException()
        } else {
            // parse error depending on Handler
            emit(errorHandler.processError(response))
        }
    } catch (e: Exception) {
        Log.d("Test", e.message ?: "")
        emit(Resource.Error(e.message ?: DEFAULT_ERROR_MESSAGE, null))
    }
}

suspend fun <T : Any> handleRetrofitWithErrorHandler(
    errorHandler: RetrofitErrorHandler,
    requestFunc: suspend () -> T
): Resource<T> {
    try {
        val response = requestFunc.invoke() as Response<T>
        if (response.isSuccessful) {
            Log.d("Test", response.code().toString())
            response.body()?.let {
                return Resource.Success(it)
            } ?: throw NullPointerException()

        } else {
            // parse error depending on Handler
            return errorHandler.processError(response)
        }

    } catch (e: Exception) {
        Log.d("Test", e.message ?: "")
        return Resource.Error(e.message ?: DEFAULT_ERROR_MESSAGE, null)
    }
}

suspend fun <T : Any> handleRetrofitWithErrorHandler(
    errorHandler: (Response<T>) -> Resource<T>,
    requestFunc: suspend () -> T
): Resource<T> {
    try {
        val response = requestFunc.invoke() as Response<T>
        if (response.isSuccessful) {
            Log.d("Test", response.code().toString())
            response.body()?.let {
                return Resource.Success(it)
            } ?: throw NullPointerException()

        } else {
            // parse error depending on Handler
            return errorHandler.invoke(response)
        }

    } catch (e: Exception) {
        Log.d("Test", e.message ?: "")
        return Resource.Error(e.message ?: DEFAULT_ERROR_MESSAGE, null)
    }
}


suspend fun <T : Any> handleRetrofitWithDbAndErrorHandler(
    errorHandler: RetrofitErrorHandler,
    requestFunc: suspend () -> T
): Resource<T> {
    try {
        val response = requestFunc.invoke() as Response<T>
        if (response.isSuccessful) {
            Log.d("Test", response.code().toString())
            response.body()?.let {
                return Resource.Success(it)
            } ?: throw NullPointerException()

        } else {
            // parse error depending on Handler
            return errorHandler.processError(response)
        }

    } catch (e: Exception) {
        Log.d("Test", e.message ?: "")
        return Resource.Error(e.message ?: DEFAULT_ERROR_MESSAGE, null)
    }
}

inline fun <reified T> Resource<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is Resource.Success) {
        data?.apply {
            callback(this)
        }
    }
}

fun <T> checkResult(response: Resource<T>): Boolean {
    return when (response) {
        is Resource.Success -> {
            true
        }
        else -> {
            false
        }
    }
}

fun <T> getError(response: Resource<T>): String {
    return when (response) {
        is Resource.Error -> {
            response.message ?: ""
        }
        else -> ""
    }
}
