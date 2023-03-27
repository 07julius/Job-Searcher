package de.flerbuster.jobsearcherandroid.api.results.jobs

import de.flerbuster.jobsearcherandroid.api.results.jobs.aggregierungen.Aggregierungen
import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Embedded
import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Job
import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Links
import de.flerbuster.jobsearcherandroid.api.results.jobs.facetten.Facette
import de.flerbuster.jobsearcherandroid.api.results.jobs.page.Page
import de.flerbuster.jobsearcherandroid.api.results.jobs.parserResult.ParserResult
import de.flerbuster.jobsearcherandroid.api.results.jobs.woOutput.WoOutput
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