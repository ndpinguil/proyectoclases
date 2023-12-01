package com.momentodos.proyectoclases.service

import com.momentodos.proyectoclases.DTO.ProductDto
import com.momentodos.proyectoclases.mapper.ProductMapper
import com.momentodos.proyectoclases.model.ProductModel
import com.momentodos.proyectoclases.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var modeloRepository: ProductRepository

    fun list ():List<ProductModel>{
        return modeloRepository.findAll()
    }
    fun save(modelo: ProductModel): ProductModel {
        try{

            modelo.description?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La materia no debe ser nula")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(modelo: ProductModel): ProductModel {
        try {
            modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): ProductModel?{
        return modeloRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = modeloRepository.findById(id)
                ?: throw Exception("ID no disponible por hoy")
            modeloRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(modelo: ProductModel): ProductModel {
        try{
            val response = modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                id=modelo.id
                description=modelo.description
                brand=modelo.brand
                price=modelo.price
                stock=modelo.stock
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    /*Codigo producto dto*/
    fun listDto():List <ProductDto> {
        val ProductList = modeloRepository.findAll()
        val productDtolist = mutableListOf<ProductDto>()
        ProductList.map{ product->
            val productDto =ProductMapper.mapToDto((product))
        }
       /* val ProductDto = ProductMapper.mapToDto(product)*/
      /*  productDtolist.add(ProductDto)*/
        return productDtolist
    }
}