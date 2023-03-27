package de.flerbuster.jobsearcherandroid

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.flerbuster.jobsearcherandroid.api.results.jobDetails.JobDetailResponse
import de.flerbuster.jobsearcher.ui.job.SearchResponse
import de.flerbuster.jobsearcher.ui.job.SearchResponseSuccess
import de.flerbuster.jobsearcher.ui.job.SearchScreen
import de.flerbuster.jobsearcher.ui.theme.colorScheme
import de.flerbuster.jobsearcherandroid.ui.job.JobDetailItem
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var navController: NavHostController
        lateinit var coroutineScope: CoroutineScope

        var searchResult: SearchResponse by mutableStateOf(SearchResponseSuccess(SnapshotStateList()))
        var jobDetailItem: JobDetailResponse? = null

        val useSameJobHeight = mutableStateOf(true)
        val jobItemHeight = 100.dp

        // search settings
        var showSettingsMenu by mutableStateOf(false)
        val location = mutableStateOf("deutschland")
        val workType = mutableStateOf(0)
        val workTime = mutableStateOf("egal")
        val perimeter = mutableStateOf(30)

        var query: String by mutableStateOf("")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            coroutineScope = rememberCoroutineScope()

            window.statusBarColor = colorScheme.backgroundColor.toArgb()

            NavHost(navController, startDestination = "jobSearcher") {
                composable("jobSearcher") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = colorScheme.backgroundColor
                    ) {
                        Row {
                            SearchScreen()
                        }
                    }
                }

                composable("jobViewer") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = colorScheme.backgroundColor
                    ) {
                        if (jobDetailItem != null) {
                            JobDetailItem(jobDetailItem!!)
                        } else {
                            Text(text = "job detail nicht geladen!")
                        }
                    }
                }
            }
        }
    }
}