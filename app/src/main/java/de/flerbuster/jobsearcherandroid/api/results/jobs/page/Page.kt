package de.flerbuster.jobsearcherandroid.api.results.jobs.page


@kotlinx.serialization.Serializable
data class Page(
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)

