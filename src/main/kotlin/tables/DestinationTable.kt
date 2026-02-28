package org.delcom.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object DestinationTable : IntIdTable("destinations") {
    val namaWisata = varchar("nama_wisata", 150)
    val lokasi = varchar("lokasi", 150)
    val peminat = integer("peminat")
    val deskripsi = text("deskripsi")
    val imageUrl = varchar("image_url", 255)

    val rating = double("rating")
    val kategori = varchar("kategori", 50)
    val jamBuka = varchar("jam_buka", 50)
    val harga = varchar("harga", 50)
    val mapsUrl = varchar("maps_url", 255)
}