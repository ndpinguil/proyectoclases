package com.momentodos.proyectoclases.repository

import com.momentodos.proyectoclases.model.ProductModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductModel,Long?> {
    fun findById (id: Long?): ProductModel?
}