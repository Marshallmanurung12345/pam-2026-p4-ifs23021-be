package org.delcom.repositories

import org.delcom.data.DestinationRequest
import org.delcom.entities.Destination

interface IDestinationRepository {
    fun getAll(search: String? = null): List<Destination>
    fun getById(id: Int): Destination?
    fun insert(req: DestinationRequest): Destination
    fun update(id: Int, req: DestinationRequest): Destination?
    fun delete(id: Int): Boolean
}