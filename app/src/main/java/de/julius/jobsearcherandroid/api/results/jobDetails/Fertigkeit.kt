package de.julius.jobsearcherandroid.api.results.jobDetails

import kotlinx.serialization.Serializable

@Serializable
data class Fertigkeit(
    val auspraegungen: Auspraegungen? = null,
    val hierarchieName: String? = null
)