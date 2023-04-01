package de.julius.jobsearcherandroid.ui.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.julius.jobsearcherandroid.ui.theme.colorScheme

@Composable
fun SpacerLine(top: Dp = 5.dp, bottom: Dp = 5.dp, alpha: Float = .80f) {
    Spacer(modifier = Modifier.padding(top))
    Box(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(
                color = colorScheme.fontColor.copy(alpha),
                shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
            )
    )
    Spacer(modifier = Modifier.padding(bottom))
}
