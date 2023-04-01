package de.julius.jobsearcherandroid.ui.job

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
import de.julius.jobsearcherandroid.ui.theme.colorScheme
import de.julius.jobsearcherandroid.api.results.jobDetails.JobDetailResponse
import de.julius.jobsearcherandroid.formatter.format
import de.julius.jobsearcherandroid.text.UrlText
import de.julius.jobsearcherandroid.ui.card.Card
import de.julius.jobsearcherandroid.ui.settings.timeLimitTypes
import de.julius.jobsearcherandroid.ui.settings.workTimes
import de.julius.jobsearcherandroid.ui.settings.workTypes
import de.julius.jobsearcherandroid.ui.spacer.SpacerLine
import java.util.*

data class CardItem(
    val name: String,
    val value: String,
    val data: Any = Unit
)

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobDetailItem(jobDetail: JobDetailResponse) {
    /*var imageUrl by mutableStateOf("")
    MainActivity.coroutineScope.launch {
        imageUrl = ArbeitsApi.getLogoUrl(jobDetail.arbeitgeberHashId ?: "") ?: ""
    }*/
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

                            //LoadImageFromUrl(url = imageUrl)
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
                                            fontWeight = FontWeight.SemiBold,
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
                                                fontWeight = FontWeight.SemiBold,
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

        Card(
            usePaddingTop = false
        ) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column {
                    val employerInfo = listOf(
                        if (jobDetail.arbeitgeber != null) CardItem(
                            "Arbeitgeber: ",
                            jobDetail.arbeitgeber,
                        )
                        else null,

                        if (jobDetail.arbeitgeberAdresse != null) CardItem(
                            "Firmenadresse: ",
                            jobDetail.arbeitgeberAdresse.format(),
                        )
                        else null,

                        if (jobDetail.branchengruppe != null) CardItem(
                            "Branchengruppe: ",
                            jobDetail.branchengruppe,
                        ) else null,

                        if (jobDetail.branche != null) CardItem(
                            "Branche: ",
                            jobDetail.branche,
                        )
                        else null,

                        if (jobDetail.arbeitgeberdarstellungUrl != null) CardItem(
                            "Internetseite: ",
                            jobDetail.arbeitgeberdarstellungUrl,
                            true
                        )
                        else null
                    )

                    val generalInfo = listOf(
                        if (jobDetail.verguetung != null) CardItem(
                            "Vergütung: ",
                            jobDetail.verguetung,
                        )
                        else null,

                        if (jobDetail.befristung != null) CardItem(
                            "Befristung: ",
                            timeLimitTypes[jobDetail.befristung.toIntOrNull() ?: -1] ?: "",
                        )
                        else null,

                        if (jobDetail.anzahlOffeneStellen != null) CardItem(
                            "Anzahl freier Stellen: ",
                            jobDetail.anzahlOffeneStellen.toString(),
                        ) else null,

                        if (jobDetail.arbeitszeitmodelle != null) CardItem(
                            "Arbeitszeit: ",
                            jobDetail.arbeitszeitmodelle
                                .map { modell -> workTimes[modell] }
                                .takeIf { it.isNotEmpty() }
                                ?.joinToString(", ")
                                ?: "arbeits Zeiten nicht geladen"
                        ) else null,

                        if (jobDetail.externeUrl != null) CardItem(
                            "Internetseite: ",
                            jobDetail.externeUrl!!,
                            true
                        )
                        else null
                    )

                    (employerInfo + generalInfo).forEach { item ->
                        if (item != null) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = item.name,
                                    style = TextStyle(textAlign = TextAlign.Left),
                                    color = colorScheme.fontColor,
                                    fontSize = 13.sp
                                )

                                if ((item.data as? Boolean) == true)
                                    UrlText(text = item.value)
                                else
                                    Text(
                                        text = item.value,
                                        style = TextStyle(textAlign = TextAlign.Left),
                                        color = colorScheme.fontColor,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}
