package de.julius.jobsearcherandroid.ui.job

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import de.julius.jobsearcherandroid.api.ArbeitsApi
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Job
import de.julius.jobsearcherandroid.ui.theme.colorScheme
import de.julius.jobsearcherandroid.MainActivity
import de.julius.jobsearcherandroid.MainActivity.Companion.location
import de.julius.jobsearcherandroid.MainActivity.Companion.perimeter
import de.julius.jobsearcherandroid.MainActivity.Companion.showSettingsMenu
import de.julius.jobsearcherandroid.MainActivity.Companion.workTime
import de.julius.jobsearcherandroid.MainActivity.Companion.workType
import de.julius.jobsearcherandroid.ui.job.JobItem
import de.julius.jobsearcherandroid.ui.settings.Settings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil

sealed interface SearchResponse

class SearchResponseSuccess(
    val results: SnapshotStateList<Job>,
) : SearchResponse

class SearchResponseFailure(
    val reason: String,
    val fetchedTerm: String,
) : SearchResponse

fun <T> List<T>.toSnapshotStateList(): SnapshotStateList<T> {
    return SnapshotStateList<T>().apply {
        addAll(this@toSnapshotStateList)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalUnitApi::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen() {
    val searchScope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }

    val itemsPerPage = 25
    var page = 1
    var maxPage = 0

    fun fetchResults(typing: Boolean) {
        val fetchTerm = MainActivity.query
        val _location = location.value
        val _workType = workType.value
        val _workTime = workTime.value
        val _perimeter = perimeter.value

        loading = true

        searchScope.launch {
            if (typing) {
                delay(300)
                if (
                    MainActivity.query != fetchTerm
                    || _location != location.value
                    || _workType != workType.value
                    || _workTime != workTime.value
                    || _perimeter != perimeter.value
                ) return@launch
            }

            val result = kotlin.runCatching {
                ArbeitsApi.getJobs(
                    MainActivity.query,
                    location.value,
                    angebotsart = workType.value.takeIf { it != 0 },
                    arbeitszeit = workTime.value.takeIf { it != "egal" },
                    umkreis = perimeter.value
                )
            }
            if (MainActivity.query == fetchTerm) {
                result.onFailure { exc ->
                    println("Failed to fetch search results for '${fetchTerm}' (${exc.message})")
                    loading = false
                    MainActivity.searchResult = SearchResponseFailure(
                        (exc.message ?: "unknown reason") + " (${exc::class.simpleName})",
                        fetchTerm
                    )
                }.onSuccess {
                    MainActivity.searchResult =
                        SearchResponseSuccess(((it?.stellenangebote ?: listOf()).toSnapshotStateList()))
                    maxPage = ceil((it?.maxErgebnisse?.toDouble()?.div(itemsPerPage) ?: 0) as Double).toInt()
                    loading = false
                }
            }
        }
    }

    fun fetchNewPage() {
        loading = true
        page++
        if (page > maxPage) searchScope.launch {
            ArbeitsApi.getJobs(
                was = MainActivity.query,
                wo = location.value,
                angebotsart = workType.value.takeIf { it != 0 },
                arbeitszeit = workTime.value.takeIf { it != "egal" },
                umkreis = perimeter.value,

                page = page
            )?.stellenangebote.also {
                (MainActivity.searchResult as SearchResponseSuccess).results += (it
                    ?: listOf()).toMutableList()
            }
        }.invokeOnCompletion { loading = false }
    }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(40.dp)
                .background(colorScheme.cardColor, RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = { showSettingsMenu = !showSettingsMenu },
                Modifier,
            ) {
                Icon(
                    Icons.Default.Settings,
                    "Settings",
                    tint = colorScheme.fontColor.copy(alpha = 0.7f)
                )
            }

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(0.75f)
                    .background(
                        color = colorScheme.fontColor.copy(alpha = 0.7f)
                    )
            )

            Spacer(Modifier.padding(start = 5.dp))

            Box(contentAlignment = Alignment.CenterStart) {
                BasicTextField(
                    MainActivity.query,
                    onValueChange = {
                        MainActivity.query = it.replace("\n", "")
                        fetchResults(true)
                    },
                    modifier = Modifier.fillMaxWidth(),

                    textStyle = TextStyle(
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        color = colorScheme.fontColor
                    ),
                    cursorBrush = Brush.horizontalGradient(0f to colorScheme.fontColor, 10f to colorScheme.fontColor),
                    maxLines = 1,
                )
                if (MainActivity.query.isEmpty()) {
                    Text("Search...", Modifier.padding(start = 4.dp), color = colorScheme.fontColor)
                }
            }
        }

        AnimatedVisibility(
            visible = showSettingsMenu,
        ) {
            Settings(location, workType, workTime, perimeter) {
                fetchResults(true)
            }
        }

        val gridState = rememberLazyListState()

        when (val currentResponse = MainActivity.searchResult) {
            is SearchResponseSuccess -> {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(500.dp),
                    state = gridState,
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    items(currentResponse.results) {item ->
                        JobItem(item)
                    }

                    if (currentResponse.results.size == 0 && MainActivity.query.isNotBlank() && !loading) {
                        item {
                            Text(
                                text = "Leider konnten keine passenden Stellenangebote gefunden werden",
                                color = colorScheme.fontColor, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    else if (currentResponse.results.size % itemsPerPage == 0 && currentResponse.results.size != 0) {
                        item {
                            LaunchedEffect(true) {
                                fetchNewPage()
                            }
                        }
                    }
                }
            }

            is SearchResponseFailure -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Text(":(", color = colorScheme.fontColor, fontSize = 30.sp)
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(
                        buildString {
                            appendLine("Did not receive a valid response for '${currentResponse.fetchedTerm}'")
                            appendLine("Reason: ${currentResponse.reason}")
                        },
                        textAlign = TextAlign.Center,
                        color = colorScheme.fontColor, fontSize = 20.sp
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.75f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorScheme.cardColor
                        ),
                        onClick = { fetchResults(false) },
                    ) {
                        Text(
                            text ="Try again",
                            color = colorScheme.fontColor,
                        )
                    }
                }
            }
        }

        if (loading) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                CircularProgressIndicator(color = colorScheme.fontColor)
            }
        }
    }
}