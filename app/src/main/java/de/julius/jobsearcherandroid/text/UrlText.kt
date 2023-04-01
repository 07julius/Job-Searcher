package de.julius.jobsearcherandroid.text

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import de.julius.jobsearcherandroid.MainActivity

@Composable
fun UrlText(
    text: String
) {
    val annotatedText = remember(text) {
        buildAnnotatedString {
            append(text)
            val startIndex = text.indexOf("http")
            if (startIndex >= 0) {
                val endIndex = text.indexOf(" ", startIndex)
                val url = text.substring(startIndex, if (endIndex >= 0) endIndex else text.length)
                addStyle(
                    style = SpanStyle(
                        color = Color(112, 112, 255, 255),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = startIndex,
                    end = startIndex + url.length
                )
                addStringAnnotation(
                    tag = "URL",
                    annotation = url,
                    start = startIndex,
                    end = startIndex + url.length
                )
            }
        }
    }

    SelectionContainer {
        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.body1,
            onClick = { offset ->
                annotatedText.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        MainActivity.context.startActivity(intent)
                    }
            }
        )
    }
}
