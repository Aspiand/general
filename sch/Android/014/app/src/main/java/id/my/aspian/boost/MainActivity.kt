package id.my.aspian.boost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import id.my.aspian.boost.ui.theme.BoostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BoostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.hashCode()
                }
            }
        }
    }
}

//@Composable
//fun Image() {
//    val apiKey = "O0uvwGxBXrzz5v16pFRsC1g6XtTJpdz86tyZsfPU"
//    val assetId = "5381a5a1-e9e3-4283-a3a6-f3b4765444c7"
//    val thumbnailBytes by produceState<ByteArray?>(initialValue = null, key1 = assetId) {
//        value = try {
//            val resp = ApiClient.service.getThumbnail(apiKey, assetId)
//            if (resp.isSuccessful) {
//                resp.body()?.bytes()
//            } else null
//        } catch (_: Exception) {
//            null
//        }
//    }
//
//    Box(contentAlignment = Alignment.Center) {
//        when {
//            thumbnailBytes == null -> {
//                CircularProgressIndicator(modifier = Modifier.size(24.dp))
//            }
//
//            else -> {
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(thumbnailBytes)
//                        .build(),
//                    contentDescription = "Thumbnail $assetId",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(100.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun Main(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(50.dp)
        ) {
            Column {
                Text(
                    text = "server",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BoostTheme {
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
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Server URL") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                ElevatedButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text("Save")
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    domain: String,
    ip: String,
    pingMs: Int,
    storagePercent: Float,
    version: String,
    mediaItems: List<MediaItem>,
    onItemClick: (MediaItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "$domain / $ip",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Ping: ${pingMs}ms", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Storage", style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { storagePercent },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .padding(vertical = 4.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Immich version: $version", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Media",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mediaItems) { item ->
                    MediaCard(
                        item = item,
                        modifier = Modifier.width(140.dp),
                        onClick = onItemClick
                    )
                }
            }
        }
    }
}