package de.flerbuster.jobsearcherandroid.formatter

import android.os.Build
import androidx.annotation.RequiresApi
import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Arbeitsort
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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
fun Instant.format(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.getDefault()).withZone(
        ZoneId.systemDefault())
    return formatter.format(toJavaInstant())
}