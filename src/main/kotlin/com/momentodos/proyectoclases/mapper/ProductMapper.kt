package com.momentodos.proyectoclases.mapper

import com.momentodos.proyectoclases.DTO.ProductDto
import com.momentodos.proyectoclases.model.ProductModel

object ProductMapper {

    fun mapToDto(Product: ProductModel): ProductDto{

        return ProductDto(
            Product.id,
            "${Product.description} ${Product.brand}"
        )
    }
}