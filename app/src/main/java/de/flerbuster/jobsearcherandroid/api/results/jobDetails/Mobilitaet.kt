package de.flerbuster.jobsearcherandroid.api.results.jobDetails


import kotlinx.serialization.Serializable

@Serializable
data class Mobilitaet(
    val reisebereitschaft: String? = null 
)