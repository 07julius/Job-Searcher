package de.flerbuster.jobsearcherandroid.api.results.jobs.embedded

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.hours

@Serializable
data class Job(
    val hashId: String? = null,
    val beruf: String? = null,
    val titel: String? = null,
    val refnr: String? = null,
    val freieBezeichnung: String? = null,
    val referenznummer: String? = null,
    val mehrereArbeitsorteVorhanden: Boolean? = null,
    val arbeitgeber: String? = null,
    val arbeitgeberHashId: String? = null,
    @SerialName("aktuelleVeroeffentlichungsdatum")
    val _aktuelleVeroeffentlichungsdatum: Eintrittsdatum? = null,
    @SerialName("modifikationsTimestamp")
    val _modifikationsTimestamp: ModificationTimestamp? = null,
    @SerialName("eintrittsdatum")
    val _eintrittsdatum: Eintrittsdatum? = null,
    val logoHashId: String? = null,
    val angebotsart: String? = null,
    val hauptDkz: String? = null,
    val anzeigeAnonym: Boolean? = null,
    val angebotsartGruppe: String? = null,
    val arbeitsort: Arbeitsort? = null,
    val externeUrl: String? = null,
    val _links: Links? = null
) {
    val modifikationsTimestamp get() = _modifikationsTimestamp?.toInstant()
    val eintrittsdatum get() = _eintrittsdatum?.toInstant()
    val aktuelleVeroeffentlichungsdatum get() = _aktuelleVeroeffentlichungsdatum?.toInstant()

    val jobHashId @RequiresApi(Build.VERSION_CODES.O)
    get() = Base64.getEncoder().encode(hashId?.toByteArray()).decodeToString()
}

@Serializable(ModificationTimestamp.ModificationTimestampSerializer::class)
class ModificationTimestamp(
    val instant: Instant
) {
    fun toInstant(): Instant = instant

    @Serializer(ModificationTimestamp::class)
    class ModificationTimestampSerializer : KSerializer<ModificationTimestamp> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        @OptIn(ExperimentalTime::class)
        override fun deserialize(decoder: Decoder): ModificationTimestamp {
            return ModificationTimestamp(Instant.parse(decoder.decodeString().take(19) + "Z").minus(2.hours))
        }

        override fun serialize(encoder: Encoder, value: ModificationTimestamp) {
            encoder.encodeString(value.instant.toString())
        }
    }
}


@Serializable(Eintrittsdatum.EintrittsdatumSerializer::class)
class Eintrittsdatum(
    val instant: Instant
) {
    fun toInstant(): Instant = instant

    @Serializer(Eintrittsdatum::class)
    class EintrittsdatumSerializer : KSerializer<Eintrittsdatum> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        @OptIn(ExperimentalTime::class)
        override fun deserialize(decoder: Decoder): Eintrittsdatum {
            return Eintrittsdatum(Instant.parse(decoder.decodeString() + "T00:00:00Z").minus(2.hours))
        }

        override fun serialize(encoder: Encoder, value: Eintrittsdatum) {
            encoder.encodeString(value.instant.toString())
        }
    }
}