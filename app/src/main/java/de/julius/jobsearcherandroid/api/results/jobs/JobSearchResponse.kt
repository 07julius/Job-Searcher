package de.julius.jobsearcherandroid.api.results.jobs

import de.julius.jobsearcherandroid.api.results.jobs.aggregierungen.Aggregierungen
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Embedded
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Job
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Links
import de.julius.jobsearcherandroid.api.results.jobs.facetten.Facette
import de.julius.jobsearcherandroid.api.results.jobs.page.Page
import de.julius.jobsearcherandroid.api.results.jobs.parserResult.ParserResult
import de.julius.jobsearcherandroid.api.results.jobs.woOutput.WoOutput
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class JobSearchResponse(
    @SerialName("_embedded") val embedded: Embedded? = null,
    @SerialName("_links") val links: Links? = null,
    val maxErgebnisse: Int? = null,
    val page: Page? = null,
    val facetten: List<Facette>? = null,
    val aggregierungen: Aggregierungen? = null,
    val parserResult: ParserResult? = null
)

@kotlinx.serialization.Serializable
data class NewJobSearchResponse(
    @SerialName("_embedded") val embedded: Embedded? = null,
    val stellenangebote: List<Job>? = null,
    @SerialName("_links") val links: Links? = null,
    val maxErgebnisse: Int? = null,
    val page: Int? = null,
    val size: Int? = null,
    val woOutput: WoOutput? = null,
    val facetten: HashMap<String, Facette>? = null,
    val aggregierungen: Aggregierungen? = null,
    val parserResult: ParserResult? = null
)