package org.delcom.repositories

import org.delcom.dao.DestinationDAO
import org.delcom.data.DestinationRequest
import org.delcom.entities.Destination
import org.delcom.entities.toDestination
import org.delcom.tables.DestinationTable
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like

class DestinationRepository : IDestinationRepository {

    override fun getAll(search: String?): List<Destination> = transaction {
        val query = if (!search.isNullOrBlank()) {
            val s = "%${search.lowercase()}%"
            DestinationDAO.find {
                (DestinationTable.namaWisata.lowerCase() like s) or
                (DestinationTable.lokasi.lowerCase() like s) or
                (DestinationTable.kategori.lowerCase() like s)
            }
        } else {
            DestinationDAO.all()
        }

        query.map { it.toDestination() }
    }

    override fun getById(id: Int): Destination? = transaction {
        DestinationDAO.findById(id)?.toDestination()
    }

    override fun insert(req: DestinationRequest): Destination = transaction {
        val row = DestinationDAO.new {
            namaWisata = req.namaWisata
            lokasi = req.lokasi
            peminat = req.peminat
            deskripsi = req.deskripsi
            imageUrl = req.imageUrl
            rating = req.rating
            kategori = req.kategori
            jamBuka = req.jamBuka
            harga = req.harga
            mapsUrl = req.mapsUrl
        }
        row.toDestination()
    }

    override fun update(id: Int, req: DestinationRequest): Destination? = transaction {
        val row = DestinationDAO.findById(id) ?: return@transaction null
        row.namaWisata = req.namaWisata
        row.lokasi = req.lokasi
        row.peminat = req.peminat
        row.deskripsi = req.deskripsi
        row.imageUrl = req.imageUrl
        row.rating = req.rating
        row.kategori = req.kategori
        row.jamBuka = req.jamBuka
        row.harga = req.harga
        row.mapsUrl = req.mapsUrl
        row.toDestination()
    }

    override fun delete(id: Int): Boolean = transaction {
        val row = DestinationDAO.findById(id) ?: return@transaction false
        row.delete()
        true
    }
}