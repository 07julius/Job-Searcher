package de.julius.jobsearcherandroid.api.results.jobs.woOutput

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Suchmodus.SuchmodusSerializer::class)
sealed class Suchmodus(
    val value: String
) {
    object Umkreissuche : Suchmodus("UMKREISSUCHE")
    object Landsuche : Suchmodus("LANDSUCHE")
    object Bundeslandsuche : Suchmodus("BUNDESLANDSUCHE")
    class Unknown(value: String) : Suchmodus(value)

    class SuchmodusSerializer : KSerializer<Suchmodus> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): Suchmodus {
            return when(val value = decoder.decodeString()) {
                "UMKREISSUCHE" -> Umkreissuche
                "LANDSUCHE" -> Landsuche
                "BUNDESLANDSUCHE" -> Bundeslandsuche
                else -> Unknown(value)
            }
        }

        override fun serialize(encoder: Encoder, value: Suchmodus) {
            encoder.encodeString(value.value)
        }

    }
}