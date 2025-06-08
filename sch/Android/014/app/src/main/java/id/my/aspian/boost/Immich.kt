package id.my.aspian.boost

public interface ImmichService {
}

data class ImmichAsset(
    val id: String,
    val checksum: String,
)

data class ImmichAlbum(
    val id: String
)

class Immich {
}