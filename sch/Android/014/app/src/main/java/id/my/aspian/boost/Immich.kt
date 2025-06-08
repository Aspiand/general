package id.my.aspian.boost

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Streaming

data class ImmichAsset(
    val id: String,

    @SerializedName("originalFileName")
    val name: String,

    val size: Int
)

data class ImmichAlbum(
    val id: String,

    @SerializedName("albumName")
    val name: String,

    val assets: List<ImmichAsset>
)

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
    ): List<ImmichAlbum>
}