package com.example.cred_assignment.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Content(
    @SerialName("items") val items: List<Item> = listOf()
)

@Serializable
data class Item(
    @SerialName("open_state") val openState: OpenState = OpenState(),

    @SerialName("closed_state") val closedState: ClosedState = ClosedState(),

    @SerialName("cta_text") val ctaText: String = "",
)

//-------

@Serializable
data class ClosedState(
    @SerialName("body") val body: ClosedStateBody = ClosedStateBody(),
)

@Serializable
data class ClosedStateBody(
    @SerialName("key1") val key1: String? = null,
    @SerialName("key2") val key2: String? = null,
)

//-------

@Serializable
data class OpenState(
    @SerialName("body") val body: OpenStateBody = OpenStateBody(),
)

@Serializable
data class OpenStateBody(
    @SerialName("title") val title: String = "",

    @SerialName("subtitle") val subtitle: String = "",

    @SerialName("card") val card: Card? = null,

    @SerialName("items") val items: List<OpenStateBodyItem>? = null,

    @SerialName("footer") val footer: String = ""
)

@Serializable
data class Card(
    @SerialName("header") val header: String = "",

    @SerialName("description") val description: String = "",

    @SerialName("max_range") val maxRange: Int = 100,

    @SerialName("min_range") val minRange: Int = 0,
)

@Serializable
data class OpenStateBodyItem(
    @SerialName("emi") val emi: String? = null,

    @SerialName("duration") val duration: String? = null,

    @SerialName("title") val title: String = "",

    @SerialName("subtitle") val subtitle: String? = null,

    @SerialName("tag") val tag: String? = null,
)
