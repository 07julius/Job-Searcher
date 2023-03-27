package de.flerbuster.jobsearcherandroid.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.flerbuster.jobsearcher.ui.theme.colorScheme
import de.flerbuster.jobsearcherandroid.MainActivity
import de.flerbuster.jobsearcherandroid.ui.selector.Selector
import de.flerbuster.jobsearcherandroid.ui.slider.Slider


val workTypes = mapOf(
    0 to "alles",
    1 to "arbeit",
    2 to "selbstständigkeit",
    4 to "ausbildung",
    24 to "praktikum"
)

val timeLimitTypes = mapOf(
    1 to "befristet",
    2 to "unbefristet"
)

val workTimes = mapOf(
    "egal" to "egal",
    "VOLLZEIT" to "vollzeit",
    "TEILZEIT" to "teilzeit",
    "SCHICHT_NACHTARBEIT_WOCHENENDE" to "schicht/nacht/wochenend-arbeit",
    "HEIM_TELEARBEIT" to "heim/tele-arbeit",
    "MINIJOB" to "minijob"
)

@Composable
fun SpacerLine() =
    Box(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(
                color = colorScheme.fontColor,
                shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
            )
    )

@Composable
fun Settings(
    location: MutableState<String>,
    workType: MutableState<Int>,
    workTime: MutableState<String>,
    perimeter: MutableState<Int>,

    onChange: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 15.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = 7.dp,
        color = colorScheme.cardColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
            ) {
                // settings top bar
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Settings",
                        style = TextStyle(textAlign = TextAlign.Center),
                        textAlign = TextAlign.Center,
                        color = colorScheme.fontColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))

                SpacerLine()

                Spacer(modifier = Modifier.padding(top = 15.dp))

                // location input
                Row {
                    Text(
                        "gleiche Kartenhöhe: ",
                        color = colorScheme.fontColor,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Switch(
                        checked = MainActivity.useSameJobHeight.value,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = colorScheme.fontColor,
                            checkedTrackColor = colorScheme.backgroundColor.copy(0.75f),
                            uncheckedThumbColor  = colorScheme.fontColor,
                            uncheckedTrackColor = colorScheme.fontColor.copy(0.75f),
                       ),
                        onCheckedChange = { MainActivity.useSameJobHeight.value = it; onChange() }
                    )
                }

                Spacer(modifier = Modifier.padding(top = 15.dp))

                SpacerLine()

                Spacer(modifier = Modifier.padding(top = 10.dp))


                // location input
                Row {
                    Text(
                        "location: ",
                        color = colorScheme.fontColor,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    BasicTextField(
                        value = location.value,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onValueChange = { location.value = it; onChange() },
                        cursorBrush = Brush.horizontalGradient(
                            0f to colorScheme.fontColor,
                            10f to colorScheme.fontColor
                        ),
                        textStyle = TextStyle(
                            color = colorScheme.fontColor,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))

                SpacerLine()

                Spacer(modifier = Modifier.padding(top = 10.dp))

                // work type input
                Row {
                    Text(
                        "Arbeits Typ: ",
                        color = colorScheme.fontColor,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Selector(
                        values = workTypes.keys.toList(),
                        selectedValue = workType,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        valueString = { workTypes[it] ?: "typ $it konnte nicht gefunden werden" },
                        onValueSelected = {
                            workType.value = it
                            onChange()
                        })
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))

                SpacerLine()

                Spacer(modifier = Modifier.padding(top = 10.dp))

                // work time input
                Row {
                    Text(
                        "Arbeits Zeiten: ",
                        color = colorScheme.fontColor,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Selector(
                        values = workTimes.keys.toList(),
                        selectedValue = workTime,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        valueString = { workTimes[it] ?: "typ $it konnte nicht gefunden werden" },
                        onValueSelected = {
                            workTime.value = it
                            onChange()
                        })
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))

                SpacerLine()

                // perimeter input
                Row {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append("Umkreis")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append("(")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    textDecoration = TextDecoration.Underline,
                                    color = colorScheme.fontColor
                                )
                            ) {
                                append("${perimeter.value}")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append("): ")
                            }
                        },
                        color = colorScheme.fontColor,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp
                    )

                    Slider(start = 10, end = 100, interval = 5, current = perimeter, onChange = {
                        onChange()
                    })
                }
            }
        }
    }
}