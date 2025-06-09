package id.my.aspian.boost

import com.google.gson.annotations.SerializedName

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

object Immich {
    var url: String = "http://agarta:2283/api/"
    var apiKey: String = "AnDl2ktiHMhesx6Q30mHBa6ytVxMB7vy9GFLbaGPOw"
}
