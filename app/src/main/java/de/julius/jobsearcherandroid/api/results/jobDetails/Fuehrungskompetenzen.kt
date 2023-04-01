package de.julius.jobsearcherandroid.api.results.jobDetails

import kotlinx.serialization.Serializable

@Serializable
data class Fuehrungskompetenzen(
    val hatBudgetverantwortung: Boolean? = null,
    val hatVollmacht: Boolean? = null
)