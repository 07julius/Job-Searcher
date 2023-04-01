package de.julius.jobsearcherandroid.api.results.jobs.aggregierungen


@kotlinx.serialization.Serializable
data class Aggregierungen(
        val bundesland: Bundesland,
        val plzebene2: HashMap<String, Int>
)
