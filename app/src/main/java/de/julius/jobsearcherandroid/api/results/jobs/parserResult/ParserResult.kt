package de.julius.jobsearcherandroid.api.results.jobs.parserResult

import de.julius.jobsearcherandroid.api.results.jobs.embedded.Koordinaten


@kotlinx.serialization.Serializable
data class ParserResult(
    val result: String,
    val koordinaten: List<Koordinaten>,
    val suchmodus: String
)
