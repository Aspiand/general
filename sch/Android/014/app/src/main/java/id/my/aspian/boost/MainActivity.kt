package id.my.aspian.boost

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade
import id.my.aspian.boost.ui.theme.BoostTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BoostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.hashCode()

                    var assets by remember { mutableStateOf<List<Asset>>(emptyList()) }
                    var hostname by remember { mutableStateOf("Loading...") }
                    var pingMs by remember { mutableStateOf(-1) }
                    var storagePercent by remember { mutableStateOf(0f) }
                    var immichVersion by remember { mutableStateOf("Unknown") }

                    LaunchedEffect(Unit) {
                        try {
                            val response = ApiClient.service.getAssets()

                            if (response.assets.items.isEmpty()) {
                                Log.d("INFO", "Tidak ada asset ditemukan.")
                            }

                            assets = response.assets.items
                        } catch (e: Exception) {
                            Log.e("ERROR", "Gagal mengambil assets", e)
                        }

//                        try {
//                            val response = ApiClient.service.getAlbum("a8a5a5b2-1b40-4a32-81db-30dac270f2a8")
//
//                            if (response.assets.isEmpty()) {
//                                Log.d("INFO", "Tidak ada asset ditemukan.")
//                            }
//
//                            assets = response.assets
//                        } catch (e: Exception) {
//                            Log.e("ERROR", "Gagal mengambil assets", e)
//                        }


                        while (isActive) {
                            try {
                                val startTime = System.currentTimeMillis()
                                ApiClient.service.ping()
                                val endTime = System.currentTimeMillis()

                                val serverInfo = ApiClient.service.getServerInfo()
                                val storageInfo = ApiClient.service.getStorageInfo()

                                hostname = Immich.url
                                storagePercent = storageInfo.diskUsagePercentage
                                immichVersion = serverInfo.version
                                pingMs = (endTime - startTime).toInt()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                            delay(1000)
                        }
                    }

                    Column {
                        ServerInfoCard(
                            hostname,
                            pingMs,
                            storagePercent,
                            immichVersion
                        )

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                content = {
                                    items(assets) { asset ->
                                        AssetCard(asset, Modifier.padding(8.dp))
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
//    ServerInfoCard(
//        "http://localhost:2283/api",
//        pingMs = 20,
//        storagePercent = 10,
//        immichVersion = "v1.123.0"
//    )
}

@Composable
fun ServerInfoCard(
    hostname: String,
    pingMs: Int,
    storagePercent: Float,
    immichVersion: String
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = hostname,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoRow(label = "Ping", value = "$pingMs ms")
            InfoRow(label = "Version", value = immichVersion)
            InfoRow(label = "Storage") {
                LinearProgressIndicator(
                    progress = { storagePercent / 100f },
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .padding(14.dp, 0.dp)
                        .clip(RoundedCornerShape(4.dp)),
                )
                Text(
                    text = "$storagePercent%",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String? = null,
    content: @Composable RowScope.() -> Unit = {
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        content()
    }
}

@Composable
fun AssetCard(asset: Asset, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            ImageViewer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.8f),
                assetId = asset.id,
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.25f)
                    .padding(8.dp),
            ) {
                Text(
                    text = asset.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                )

//                Spacer(modifier = Modifier.width(8.dp))
//
//                Text(
//                    text = formatSize(asset.exifInfo.size),
//                    style = MaterialTheme.typography.bodySmall
//                )
            }
        }
    }
}

fun formatSize(bytes: Int): String {
    val kb = bytes / 1024f
    return if (kb > 1024) {
        "%.2f MB".format(kb / 1024)
    } else {
        "%.0f KB".format(kb)
    }
}

@Composable
fun ImageViewer(modifier: Modifier = Modifier, assetId: String, size: String = "thumbnail") {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${Immich.url}assets/${assetId}/thumbnail?size=${size}")
            .crossfade(true)
            .httpHeaders(
                headers = NetworkHeaders.Builder()
                    .set("x-api-key", Immich.apiKey)
                    .build()
            ).build(),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Settings(
    initialValue: Immich = Immich,
    onSave: (Immich) -> Unit = {}
) {
    var url by remember { mutableStateOf(initialValue.url) }

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(28.dp)) {
                Text(
                    text = "Server Configuration",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = url,
                    onValueChange = { url = it },
                    label = { Text("URL") },
                    shape = RoundedCornerShape(58.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

//                Spacer(modifier = Modifier.height(20.dp))

                ElevatedButton(
                    onClick = {
//                        if (url.isNotBlank()) {}
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
            }
        }
    }
}
