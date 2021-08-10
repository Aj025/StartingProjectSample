package sample.project.data.api

/**
 * Sample Retrofit Declaration
 */

//class NetworkModule {
//
//    private val moshi by lazy {
//        val moshiBuilder = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//        moshiBuilder.build()
//    }
//
//    private val loggingInterceptor by lazy {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        loggingInterceptor
//    }
//
//    private val httpClient by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    private fun getRetrofit(endpointURL: String): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(endpointURL)
//            .client(httpClient)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .build()
//    }
//
//    fun createBooksApi(endpointURL: String): BooksApi {
//        val retrofit = getRetrofit(endpointURL)
//        return retrofit.create(BooksApi::class.java)
//    }
//}