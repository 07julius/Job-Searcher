package de.julius.jobsearcherandroid.image

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun LoadImageFromUrl(url: String) {
    var image by remember { mutableStateOf<Painter?>(null) }
    val context = LocalContext.current

    LaunchedEffect(url) {
        image = loadImage(url, context)
    }

    image?.let { painter ->
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

fun loadImage(url: String, context: Context): Painter? {
    var inputStream: InputStream? = null
    var httpURLConnection: HttpURLConnection? = null

    try {
        val imageUrl = URL(url)
        httpURLConnection = imageUrl.openConnection() as HttpURLConnection
        httpURLConnection.connect()
        inputStream = httpURLConnection.inputStream

        val byteArray = inputStream.readBytes()
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        val imageBitmap = bitmap.asImageBitmap()

        return BitmapPainter(imageBitmap)
    } catch (e: Exception) {
        // Handle the exception as per your requirement
        e.printStackTrace()
    } finally {
        httpURLConnection?.disconnect()
        inputStream?.close()
    }

    return null
}