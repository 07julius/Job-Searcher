package de.julius.jobsearcherandroid.api.results.jobDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Koordinaten(
    val lat: Double? = null, 
    val lon: Double? = null 
)