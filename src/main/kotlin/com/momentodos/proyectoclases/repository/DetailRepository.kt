package com.momentodos.proyectoclases.repository

import com.momentodos.proyectoclases.model.DetailModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository  : JpaRepository<DetailModel, Long?> {
    fun findById (id: Long?): DetailModel?
}