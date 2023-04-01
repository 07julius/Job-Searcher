package de.julius.jobsearcherandroid.ui.job

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.julius.jobsearcherandroid.api.ArbeitsApi
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Job
import de.julius.jobsearcherandroid.ui.theme.colorScheme
import de.julius.jobsearcherandroid.MainActivity
import de.julius.jobsearcherandroid.ui.card.Card
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JobItem(job: Job, modifier: Modifier = Modifier) {
    val modifier = if (MainActivity.useSameJobHeight.value) modifier.height(MainActivity.jobItemHeight) else modifier
    Card(
        modifier = modifier
            .clickable {
                MainActivity.coroutineScope.launch {
                    MainActivity.jobDetailItem = ArbeitsApi.getJob(job.jobHashId).also {
                        it?.externeUrl = job.externeUrl
                    }
                }.invokeOnCompletion {
                    MainActivity.navController.navigate("jobViewer")
                }
            },
        usePaddingStart = false,
        usePaddingEnd = false,
        usePaddingBottom = false,
        usePaddingTop = false
    ) {
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
                    Row {
                        Text(
                            job.titel ?: "no title found",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = colorScheme.fontColor
                        )
                    }
                    Text("by ${job.arbeitgeber ?: "no arbeitsgeber found"}", color = colorScheme.fontColor)
                }
            }
        }
    }
}