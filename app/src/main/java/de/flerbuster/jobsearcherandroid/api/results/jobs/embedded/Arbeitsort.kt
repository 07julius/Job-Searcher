package de.flerbuster.jobsearcherandroid.api.results.jobs.embedded

@kotlinx.serialization.Serializable
data class Arbeitsort(
    val ort: String? = null,
    val plz: String? = null,
    val region: String? = null,
    val land: String? = null,
    val strasse: String? = null,
    val koordinaten: Koordinaten? = null,
    val strasseHausnummer: String? = null, 
    val entfernung: String? = null
) {
    override fun toString(): String {
        return "${if (ort != null) "Ort: $ort, " else ""}${if (region != null) "Region: $region, " else ""}${if (land != null) "Land: $land, " else ""}${if (koordinaten != null) "Koordinaten: $koordinaten, " else ""}${if (entfernung != null) "Entfernung: $entfernung" else ""}"
    }
}
