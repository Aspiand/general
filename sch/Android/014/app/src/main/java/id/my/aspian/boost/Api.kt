package id.my.aspian.boost

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Streaming
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "http://192.168.7.2:2283/api/"
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
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

    @GET("albums")
    suspend fun getAllAlbums(
        @Header("x-api-key") apiKey: String
    )
}