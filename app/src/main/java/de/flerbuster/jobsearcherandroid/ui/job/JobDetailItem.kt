package de.flerbuster.jobsearcherandroid.ui.job

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.flerbuster.jobsearcher.ui.theme.colorScheme
import de.flerbuster.jobsearcherandroid.api.results.jobDetails.JobDetailResponse
import de.flerbuster.jobsearcherandroid.formatter.format
import de.flerbuster.jobsearcherandroid.ui.card.Card
import de.flerbuster.jobsearcherandroid.ui.settings.workTimes
import de.flerbuster.jobsearcherandroid.ui.settings.workTypes
import de.flerbuster.jobsearcherandroid.ui.spacer.SpacerLine
import java.util.*

data class CardItem(
    val name: String,
    val value: String
)

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobDetailItem(jobDetail: JobDetailResponse) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Card {
            Row(
                Modifier
                    .fillMaxHeight()
                    .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = jobDetail.titel ?: "titel nicht gefunden",
                            style = TextStyle(textAlign = TextAlign.Start),
                            color = colorScheme.fontColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )

                        Spacer(Modifier.padding(3.dp))

                        Row {
                            Text(
                                text = jobDetail.arbeitgeber ?: "arbeitgeber nicht gefunden",
                                style = TextStyle(textAlign = TextAlign.Start),
                                color = colorScheme.fontColor,
                                fontSize = 13.sp
                            )

                        }

                        SpacerLine(alpha = .30f)

                        Text(
                            text = jobDetail.branche ?: jobDetail.branchengruppe ?: "",
                            style = TextStyle(textAlign = TextAlign.Start),
                            color = colorScheme.fontColor,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start
                        )
                        if (jobDetail.ersteVeroeffentlichungsdatum != null) Text(
                            text = "veröffentlicht ${jobDetail.ersteVeroeffentlichungsdatum.instant.format()}",
                            style = TextStyle(textAlign = TextAlign.Start),
                            color = colorScheme.fontColor,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Right
                        )

                        SpacerLine(alpha = .30f)

                        val workInfo = listOf(
                            if (jobDetail.angebotsart != null) CardItem(
                                "Typ:",
                                workTypes[jobDetail.angebotsart.toIntOrNull()] ?: "angebotsart nicht geladen"
                            )
                            else null,

                            if (jobDetail.arbeitszeitmodelle != null) CardItem(
                                "Arbeitszeit:",
                                jobDetail.arbeitszeitmodelle
                                    .map { modell -> workTimes[modell] }
                                    .takeIf { it.isNotEmpty() }
                                    ?.joinToString(", ")
                                    ?: "arbeitsZeiten nicht geladen"
                            ) else null,

                            if (jobDetail.eintrittsdatum != null) CardItem(
                                "Eintrittsdatum:",
                                "ab ${jobDetail.eintrittsdatum.instant.format()}"
                            )
                            else null,

                            if (jobDetail.arbeitsorte != null) CardItem(
                                "Arbeitsort:",
                                jobDetail.arbeitsorte.map { ort -> ort?.format() }
                                    .takeIf { it.isNotEmpty() }
                                    ?.joinToString(", ")
                                    ?: "arbeitsort nicht geladen"
                            )
                            else null
                        )
                        workInfo.forEachIndexed { index, item ->
                            if (item != null && index % 2 == 0) {
                                val next = workInfo.getOrNull(index + 1)
                                val align = if (next == null) TextAlign.Center else TextAlign.Start
                                Column(
                                    modifier = Modifier
                                        .padding(3.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = item.name,
                                            style = TextStyle(textAlign = align),
                                            color = colorScheme.fontColor,
                                            fontSize = 13.sp
                                        )


                                        if (next != null) Column(
                                            Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                text = next.name,
                                                style = TextStyle(textAlign = TextAlign.Right),
                                                color = colorScheme.fontColor,
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = item.value,
                                            style = TextStyle(textAlign = align),
                                            color = colorScheme.fontColor,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )

                                        if (next != null) Column(
                                            Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                text = next.value,
                                                style = TextStyle(textAlign = TextAlign.Right),
                                                color = colorScheme.fontColor,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 15.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Card(
            usePaddingTop = false
        ) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Spacer(modifier = Modifier.padding(top = 10.dp))

                SelectionContainer {
                    Text(
                        jobDetail.stellenbeschreibung ?: "keine stellenbeschreibung vorhanden",
                        fontSize = 12.sp,
                        color = colorScheme.fontColor
                    )
                }
            }
        }
    }
}
