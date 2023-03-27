package de.flerbuster.jobsearcherandroid.ui.job

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.flerbuster.jobsearcherandroid.api.results.jobDetails.JobDetailResponse
import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Arbeitsort
import de.flerbuster.jobsearcher.ui.theme.colorScheme
import java.util.*

fun Arbeitsort.format(): String {
    var string = ""
    if (ort != null) {
        string += if (region == null || ort.contains(region, true)) "$ort, "
        else "$ort ($region), "
    }
    if (strasseHausnummer != null) string += "$strasseHausnummer, "
    else if (strasse != null) string += "$strasse, "
    return string
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobDetailItem(job: JobDetailResponse, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(135.dp)
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = 7.dp,
        color = colorScheme.cardColor
    ) {
        Row(
            Modifier
                .fillMaxHeight()
                .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Row {
                        val text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 19.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append(job.titel ?: "titel nicht geladen")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append(" by ")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append(job.arbeitgeber ?: "kein arbeitgeber")
                            }
                        }
                        Text(
                            text = text,
                            style = TextStyle(textAlign = TextAlign.Center), color = colorScheme.fontColor,
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    Box(
                        Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(
                                color = colorScheme.fontColor,
                                shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                            )
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    if ((job.arbeitsorte ?: listOf()).isNotEmpty()) Row {
                        val text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp, color = colorScheme.fontColor
                                )
                            ) {
                                append("Arbeitsort(e): ")
                            }


                            job.arbeitsorte?.forEach { ort ->
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 15.sp, color = colorScheme.fontColor
                                    )
                                ) {
                                    if (ort != job.arbeitsorte.last()) append("${ort?.format()}, ")
                                    else append("${ort?.format()}")
                                }
                            }

                        }

                        Text(
                            text = text,
                            style = TextStyle(textAlign = TextAlign.Center), color = colorScheme.fontColor,
                            textAlign = TextAlign.Left
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    Text(job.stellenbeschreibung ?: "keine stellenbeschreibung vorhanden", fontSize = 12.sp, color = colorScheme.fontColor)
                }
            }
        }
    }
}