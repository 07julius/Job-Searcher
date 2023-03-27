package de.flerbuster.jobsearcherandroid.ui.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.flerbuster.jobsearcher.ui.theme.colorScheme


class Text(
    val decoration: TextDecoration = TextDecoration.None,
    val fontSize: TextUnit = 14.sp,
    val fontWeight: FontWeight = FontWeight.Normal
)

@Composable
fun <T> Selector(
    values: List<T>,
    selectedValue: MutableState<T>,
    modifier: Modifier,
    valueString: (T) -> String,
    onValueSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val textDecorations = remember { SnapshotStateMap<T, Text?>() }

    Box(modifier = Modifier.clickable { expanded = !expanded }.fillMaxHeight()) {
        Text(
            text = valueString(selectedValue.value), modifier = modifier, color = colorScheme.fontColor,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier.background(colorScheme.backgroundColor),
            onDismissRequest = { expanded = false }
        ) {
            values.forEachIndexed { index, value ->
                if (value == selectedValue.value) textDecorations[value] = Text(
                    TextDecoration.Underline,
                    16.sp,
                    FontWeight.SemiBold
                ) else textDecorations[value] = Text()

                DropdownMenuItem(
                    onClick = {
                        onValueSelected(value)
                        expanded = false
                    }) {
                    Text(
                        text = valueString(value),
                        modifier = Modifier.padding(16.dp),
                        color = colorScheme.fontColor,
                        textDecoration = (textDecorations[value] ?: Text()).decoration,
                        fontSize = (textDecorations[value] ?: Text()).fontSize,
                        fontWeight = (textDecorations[value] ?: Text()).fontWeight
                    )
                }

                if (index != values.size - 1) Box(
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(
                            color = colorScheme.fontColor.copy(0.8f),
                            shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                        )
                )
            }
        }
    }
}
