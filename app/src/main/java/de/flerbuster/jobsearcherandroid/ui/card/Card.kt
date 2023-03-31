package de.flerbuster.jobsearcherandroid.ui.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.flerbuster.jobsearcher.ui.theme.colorScheme

@Composable
fun Card(
    modifier: Modifier = Modifier,
    usePaddingTop: Boolean = true,
    content: @Composable () -> Unit
) {
    val topPadding = if (usePaddingTop) 20.dp else 0.dp
    Surface(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = topPadding),
        shape = RoundedCornerShape(7.dp),
        elevation = 7.dp,
        color = colorScheme.cardColor
    ) { content() }
}