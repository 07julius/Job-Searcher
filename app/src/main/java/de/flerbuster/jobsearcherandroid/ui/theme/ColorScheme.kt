package de.flerbuster.jobsearcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

sealed class ColorScheme(
    open val backgroundColor: Color,
    open val cardColor: Color,
    open val fontColor: Color,
)

class LightColorScheme(
    override val backgroundColor: Color = Color(235, 235, 235),
    override val cardColor: Color = Color(255, 255, 255),
    override val fontColor: Color = Color(5, 5, 5)
) : ColorScheme(
    backgroundColor, cardColor, fontColor
)

class DarkColorScheme(
    override val backgroundColor: Color = Color(25, 25, 25),
    override val cardColor: Color = Color(45, 45, 45),
    override val fontColor: Color = Color(250, 250, 250)
) : ColorScheme(
    backgroundColor, cardColor, fontColor
)

val colorScheme: ColorScheme
    @Composable get() = if (isSystemInDarkTheme()) DarkColorScheme() else LightColorScheme()