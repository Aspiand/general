package id.my.aspian.boost

import com.google.gson.annotations.SerializedName

data class ServerInfo(
    val version: String
)

data class StorageInfo(
    val diskSize: String,
    val diskUse: String,
    val diskAvailable: String,
    val diskUsagePercentage: Float,
)

data class Asset(
    val id: String,

    @SerializedName("originalFileName")
    val name: String,

    val exifInfo: ExifInfo
)

data class ExifInfo(
    val latitude: Int,
    val longitude: Int,
)

data class AssetsWrapper(
    val total: Int,
    val count: Int,
    val items: List<Asset>
)

data class AssetsResponse(
    val assets: AssetsWrapper
)

data class ImmichAlbum(
    val id: String,

    @SerializedName("albumName")
    val name: String,

    val assets: List<Asset>
)

object Immich {
    var url: String = "https://immich.aspian.my.id/api/"
    var apiKey: String = "AnDl2ktiHMhesx6Q30mHBa6ytVxMB7vy9GFLbaGPOw"
}
