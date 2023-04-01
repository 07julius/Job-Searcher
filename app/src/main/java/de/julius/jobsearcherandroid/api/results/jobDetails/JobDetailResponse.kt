package de.julius.jobsearcherandroid.api.results.jobDetails


import de.julius.jobsearcherandroid.api.results.jobs.embedded.Arbeitsort
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Eintrittsdatum
import de.julius.jobsearcherandroid.api.results.jobs.embedded.Job
import de.julius.jobsearcherandroid.api.results.jobs.embedded.ModificationTimestamp
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class JobDetailResponse(
    val aktuelleVeroeffentlichungsdatum: Eintrittsdatum? = null,
    val allianzpartner: String? = null,
    val allianzpartnerUrl: String? = null,
    val angebotsart: String? = null,
    val anzahlOffeneStellen: Int? = null,
    val anzeigeAnonym: Boolean? = null,
    val arbeitgeber: String? = null,
    val arbeitgeberAdresse: Arbeitsort? = null,
    val arbeitgeberHashId: String? = null,
    val arbeitgeberdarstellung: String? = null,
    val arbeitgeberdarstellungUrl: String? = null,
    val arbeitsorte: List<Arbeitsort?>? = null,
    val arbeitszeitmodelle: List<String?>? = null,
    val befristung: String? = null,
    val beruf: String? = null,
    val betriebsgroesse: String? = null,
    val branche: String? = null,
    val branchengruppe: String? = null,
    val eintrittsdatum: Eintrittsdatum? = null,
    val ersteVeroeffentlichungsdatum: Eintrittsdatum? = null,
    val fertigkeiten: List<Fertigkeit?>? = null,
    val fuehrungskompetenzen: Fuehrungskompetenzen? = null,
    val fuerFluechtlingeGeeignet: Boolean? = null,
    val hashId: String? = null,
    val hauptDkz: String? = null,
    val istBetreut: Boolean? = null,
    val istGoogleJobsRelevant: Boolean? = null,
    val mobilitaet: Mobilitaet? = null,
    val modifikationsTimestamp: ModificationTimestamp? = null,
    val nurFuerSchwerbehinderte: Boolean? = null,
    val refnr: String? = null,
    val stellenbeschreibung: String? = null,
    val titel: String? = null,
    val uebernahme: Boolean? = null, 
    val verguetung: String? = null 
) {
    var externeUrl: String? = null
}