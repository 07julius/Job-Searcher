package de.flerbuster.jobsearcherandroid.api.results.jobs.facetten


@kotlinx.serialization.Serializable
data class Auswahl(
        val trefferAnzahl: Int,
        val preset: Boolean,
        val name: String
)
