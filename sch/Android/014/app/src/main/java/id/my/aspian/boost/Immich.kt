package id.my.aspian.boost

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Streaming

data class ImmichStorageInfo(
    val diskSize: String,
    val diskUse: String,
    val diskAvailable: String,
    val diskUsagePercentage: String,
)

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

data class Immich(
    val Url: String,
    val Port: Int,
)