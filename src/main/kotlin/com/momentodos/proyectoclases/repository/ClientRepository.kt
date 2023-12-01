package com.momentodos.proyectoclases.repository

import com.momentodos.proyectoclases.model.ClientModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<ClientModel, Long?> {
    fun findById (id: Long?): ClientModel?

}