package org.delcom.services

import org.delcom.data.DestinationRequest
import org.delcom.entities.Destination
import org.delcom.repositories.IDestinationRepository

class DestinationService(
    private val repo: IDestinationRepository
) {
    fun getAll(search: String? = null) = repo.getAll(search)
    fun getById(id: Int) = repo.getById(id)

    fun create(req: DestinationRequest): Destination {
        return repo.insert(req)
    }

    fun update(id: Int, req: DestinationRequest): Destination? {
        return repo.update(id, req)
    }

    fun delete(id: Int) = repo.delete(id)
}