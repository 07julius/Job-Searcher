package de.flerbuster.jobsearcherandroid.api.results.jobs.embedded

@kotlinx.serialization.Serializable
data class Embedded(
    val jobs: List<Job>
)