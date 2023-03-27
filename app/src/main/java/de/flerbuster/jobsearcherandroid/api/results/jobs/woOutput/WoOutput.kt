package de.flerbuster.jobsearcherandroid.api.results.jobs.woOutput

import de.flerbuster.jobsearcherandroid.api.results.jobs.embedded.Koordinaten

@kotlinx.serialization.Serializable
data class WoOutput(
    val bereinigterOrt: String? = null,
    val suchmodus: Suchmodus? = null,
    val koordinaten: List<Koordinaten>? = null
)
