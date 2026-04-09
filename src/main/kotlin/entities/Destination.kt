package org.delcom.entities

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Destination(
    var id: String = UUID.randomUUID().toString(),
    var nama: String,
    var slug: String,
    var kategori: String,
    var deskripsi: String,
    var lokasi: String,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var hargaTiket: Long = 0,
    var jamBuka: String? = null,
    var kontak: String? = null,
    var pathGambar: String? = null,

    val createdAt: Instant = Clock.System.now(),
    var updatedAt: Instant = Clock.System.now(),
)
