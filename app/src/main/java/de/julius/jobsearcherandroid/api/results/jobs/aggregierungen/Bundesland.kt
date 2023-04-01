package de.julius.jobsearcherandroid.api.results.jobs.aggregierungen

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Bundesland(
    @SerialName("Baden-Württemberg") val Baden_Württemberg: Int,
    val Bayern: Int,
    val Berlin: Int,
    val Brandenburg: Int,
    val Bremen: Int,
    val Hamburg: Int,
    val Hessen: Int,
    @SerialName("Mecklenburg-Vorpommern") val Mecklenburg_Vorpommern: Int,
    val Niedersachsen: Int,
    @SerialName("Nordrhein-Westfahlen") val Nordrhein_Westfalen: Int,
    @SerialName("Rheinland-Pfalz") val Rheinland_Pfalz: Int,
    val Saarland: Int,
    val Sachsen: Int,
    @SerialName("Sachsen-Anhalt") val Sachsen_Anhalt: Int,
    @SerialName("Schleswig-Holstein") val Schleswig_Holstein: Int,
    val Thüringen: Int
)
