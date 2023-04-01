package de.julius.jobsearcherandroid.ui.slider

import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import de.julius.jobsearcherandroid.ui.theme.colorScheme
import kotlin.math.roundToInt

@Composable
fun Slider(start: Int, end: Int, current: MutableState<Int>, interval: Int, onChange: (Int) -> Unit) {
    androidx.compose.material.Slider(
        value = current.value.toFloat(),
        valueRange = start.toFloat()..end.toFloat(),
        steps = (end - start) / interval,
        colors = SliderDefaults.colors(
            thumbColor = colorScheme.fontColor,
            activeTickColor = colorScheme.backgroundColor,
            activeTrackColor = colorScheme.backgroundColor,
            inactiveTickColor = colorScheme.backgroundColor.copy(0.75f),
           inactiveTrackColor = colorScheme.backgroundColor.copy(0.75f)
        ),
        onValueChange = {
            current.value = it.roundToInt()
            onChange(it.roundToInt())
            println(it)
        })
}