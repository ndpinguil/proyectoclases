package com.momentodos.proyectoclases.repository

import com.momentodos.proyectoclases.model.InvoiceModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository  : JpaRepository<InvoiceModel, Long?> {
    fun findById (id: Long?): InvoiceModel?
}