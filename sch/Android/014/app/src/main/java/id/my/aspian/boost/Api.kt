package id.my.aspian.boost

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming
import java.util.concurrent.TimeUnit

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("x-api-key", Immich.apiKey ?: "")

        return chain.proceed(requestBuilder.build())
    }
}

object ApiClient {
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(AuthInterceptor())
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Immich.url)
        .client(client)
        .build()
    val service: ImmichAPI = retrofit.create(ImmichAPI::class.java)
}

interface ImmichAPI {
    @GET("server/ping")
    suspend fun ping()

    @Streaming
    @GET("assets/{id}/thumbnail")
    suspend fun getThumbnail(
        @Header("x-api-key") apiKey: String,
        @Path("id") id: String
    ): Response<ResponseBody>

    @GET("/albums/{id}")
    suspend fun getAlbum(
        @Path("id") id: String
    ): AlbumResponse

    @POST("search/metadata")
    suspend fun getAssets(): AssetsResponse

    @GET("server/storage")
    suspend fun getStorageInfo(): StorageInfo

    @GET("server/about")
    suspend fun getServerInfo(): ServerInfo
}