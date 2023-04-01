package de.julius.jobsearcherandroid.ui.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.julius.jobsearcherandroid.ui.theme.colorScheme

@Composable
fun Card(
    modifier: Modifier = Modifier,
    usePaddingStart: Boolean = true,
    usePaddingEnd: Boolean = true,
    usePaddingBottom: Boolean = true,
    usePaddingTop: Boolean = true,
    content: @Composable () -> Unit
) {
    val startPadding = if (usePaddingStart) 20.dp else 0.dp
    val endPadding = if (usePaddingEnd) 20.dp else 0.dp
    val bottomPadding = if (usePaddingBottom) 20.dp else 0.dp
    val topPadding = if (usePaddingTop) 20.dp else 0.dp
    Surface(
        modifier = modifier
            .padding(
                start = startPadding,
                end = endPadding,
                bottom = bottomPadding,
                top = topPadding
            ),
        shape = RoundedCornerShape(7.dp),
        elevation = 7.dp,
        color = colorScheme.cardColor
    ) { content() }
}