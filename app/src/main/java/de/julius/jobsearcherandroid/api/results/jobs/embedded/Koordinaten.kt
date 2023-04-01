package de.julius.jobsearcherandroid.api.results.jobs.embedded


@kotlinx.serialization.Serializable
data class Koordinaten(
    val lat: Double? = null,
    val lon: Double? = null
) {
    override fun toString(): String {
        return "${if (lat != null) "lat: $lat, " else ""}${if (lon != null) "lon: $lon" else ""}"
    }
}