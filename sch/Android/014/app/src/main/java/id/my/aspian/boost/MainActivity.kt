package id.my.aspian.boost

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.graphics.Color
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

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val assetId = "e2796a84-b4df-4010-8490-e9b354f3b941"

        setContent {
            BoostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.hashCode()

                    var items by remember { mutableStateOf<List<Asset>>(emptyList()) }

                    LaunchedEffect(true) {
                        try {
                            val response = ApiClient.service.getAssets()

                            if (response.assets.items.isEmpty()) {
                                Log.d("INFO", "Tidak ada asset ditemukan.")
                            }

                            items = response.assets.items
                        } catch (e: Exception) {
                            Log.e("ERROR", "Gagal mengambil assets", e)
                        }
                    }

                    for (i in items) {
                        AssetCard(i)
                        break
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
fun AssetCard(asset: Asset) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            ImageViewer(
                assetId = asset.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = asset.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = formatSize(asset.size),
                style = MaterialTheme.typography.bodySmall
            )
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


///////////////////////////////////
@Composable
fun ServerInfoCard(
    hostname: String,
    pingMs: Int,
    storagePercent: Int,
    immichVersion: String
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
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
fun ImageViewer(modifier: Modifier = Modifier, assetId: String, size: String = "thumbnail") {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("http://192.168.7.2:2283/api/assets/${assetId}/thumbnail?size=${size}")
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
