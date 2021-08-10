package simple.program.domain.util


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode : Int? = null,
    val errorSlug : String? = null
) {
    class Success<T>( data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, errorCode: Int? = null, errorSlug: String? = null) : Resource<T>(data, message, errorCode, errorSlug)
    class Loading<T> : Resource<T>()
}