package de.julius.jobsearcherandroid.api.results.jobs.facetten

import kotlinx.serialization.Serializable


@Serializable
data class Facette(
        val counts: Map<String, Int>,
        val maxCount: Int
)