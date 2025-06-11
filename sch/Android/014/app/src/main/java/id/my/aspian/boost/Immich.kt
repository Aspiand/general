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

data class AlbumResponse(
    @SerializedName("id") val id: String,
    @SerializedName("albumName") val name: String,
    @SerializedName("albumThumbnailAssetId") val thumbnailId: String? = null,
    @SerializedName("assetCount") val assetCount: Int,
    @SerializedName("assets") val assets: List<Asset>
)

object Immich {
    var url: String = "http://100.64.1.222:2283/api/"
    var apiKey: String = "UIa0nF2czcf0snSbjaInYjAWiAhNPzOCF7OB0IYLrHRk"
}
