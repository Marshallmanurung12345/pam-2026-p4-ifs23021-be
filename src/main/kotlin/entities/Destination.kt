package org.delcom.entities

import kotlinx.serialization.Serializable

@Serializable
data class Destination(
    val id: Int,
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

fun org.delcom.dao.DestinationDAO.toDestination(): Destination = Destination(
    id = this.id.value,
    namaWisata = this.namaWisata,
    lokasi = this.lokasi,
    peminat = this.peminat,
    deskripsi = this.deskripsi,
    imageUrl = this.imageUrl,
    rating = this.rating,
    kategori = this.kategori,
    jamBuka = this.jamBuka,
    harga = this.harga,
    mapsUrl = this.mapsUrl
)