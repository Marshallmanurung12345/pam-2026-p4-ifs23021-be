package org.delcom.dao

import org.delcom.tables.DestinationTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class DestinationDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DestinationDAO>(DestinationTable)

    var namaWisata by DestinationTable.namaWisata
    var lokasi by DestinationTable.lokasi
    var peminat by DestinationTable.peminat
    var deskripsi by DestinationTable.deskripsi
    var imageUrl by DestinationTable.imageUrl

    var rating by DestinationTable.rating
    var kategori by DestinationTable.kategori
    var jamBuka by DestinationTable.jamBuka
    var harga by DestinationTable.harga
    var mapsUrl by DestinationTable.mapsUrl
}