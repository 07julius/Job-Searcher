package de.flerbuster.jobsearcherandroid.api.results.jobs.embedded

@kotlinx.serialization.Serializable
data class Links(
    val details: Link,
    val arbeitgeberlogo: Link,
    val jobdetails: Link,
    val first: Link,
    val self: Link,
    val next: Link,
    val last: Link
)


@kotlinx.serialization.Serializable
class Link(
    val href: String
)