package de.julius.jobsearcherandroid.api.results.jobs.embedded

@kotlinx.serialization.Serializable
data class Embedded(
    val jobs: List<Job>
)