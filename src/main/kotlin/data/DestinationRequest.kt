package org.delcom.data

import kotlinx.serialization.Serializable

@Serializable
data class DestinationRequest(
    val namaWisata: String,
    val lokasi: String,
    val peminat: Int,
    val deskripsi: String,
    val imageUrl: String,
    val rating: Double,
    val kategori: String,
    val jamBuka: String,
    val harga: String,
    val mapsUrl: String
)
