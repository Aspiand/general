package id.my.aspian.boost

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import id.my.aspian.boost.ui.theme.BoostTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiKey = "O9aApOV7tDIk25K0NT0LU0ZrYRtNxjaHmR2brTwaQ"
        val assetId = "5381a5a1-e9e3-4283-a3a6-f3b4765444c7"

        setContent {
            BoostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.hashCode()
                    // rememberCoroutineScope().launch {
                    //    ApiClient.service.ping()
                    // }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    ServerInfoCard(
        "http://localhost:2283/api",
        pingMs = 20,
        storagePercent = 10,
        immichVersion = "v1.123.0"
    )
//    Settings()
}

@Composable
fun ThumbnailViewer(
    apiKey: String,
    assetId: String
) {
    val context = LocalContext.current

    val thumbnailBytes by produceState<ByteArray?>(initialValue = null, key1 = assetId) {
        value = try {
            val response = ApiClient.service.getThumbnail(apiKey, assetId)
            if (response.isSuccessful) {
                response.body()?.bytes()
            } else null
        } catch (e: Exception) {
            null
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            thumbnailBytes == null -> {
                CircularProgressIndicator()
            }

            else -> {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(thumbnailBytes)
                        .build(),
                    contentDescription = "Thumbnail",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun AlbumGrid(apiKey: String) {
    val context = LocalContext.current
    var items by remember { mutableStateOf<List<ImmichAsset>>(emptyList()) }

    LaunchedEffect(true) {
        try {
            val albums = ApiClient.service.getAllAlbums(apiKey)
            if (albums.size <= 0) {
                Log.d("ERROR", "Tidak ada assetttt")
            }
            items = albums.firstOrNull()?.assets ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { asset ->
            AssetCard(apiKey = apiKey, asset = asset)
        }
    }
}

@Composable
fun AssetCard(apiKey: String, asset: ImmichAsset) {
    val context = LocalContext.current

    val thumbnail by produceState<ByteArray?>(initialValue = null, key1 = asset.id) {
        value = try {
            val resp = ApiClient.service.getThumbnail(apiKey, asset.id)
            if (resp.isSuccessful) resp.body()?.bytes() else null
        } catch (_: Exception) {
            null
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (thumbnail == null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            } else {
                Image(
                    bitmap = BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail!!.size)
                        .asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

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
fun Settings() {
    var text by remember { mutableStateOf("") }

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

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("URL") },
                    shape = RoundedCornerShape(58.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Port") },
                    shape = RoundedCornerShape(58.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                ElevatedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text("Save")
                }
            }
        }
    }
}