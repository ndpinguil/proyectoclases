package com.momentodos.proyectoclases.controller

import com.momentodos.proyectoclases.model.ProductModel
import com.momentodos.proyectoclases.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ProductModel")
class ProductController {
    @Autowired
    lateinit var modeloService: ProductService

    @GetMapping
    fun list(): List<ProductModel> {
        return modeloService.list()
    }

    @PostMapping
    fun save(@RequestBody modelo: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(modeloService.save(modelo), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody modelo: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(modeloService.update(modelo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(modeloService.listById(id), HttpStatus.OK)
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean? {
        return modeloService.delete(id)
    }
    @PatchMapping
    fun updateName (@RequestBody modelo: ProductModel): ResponseEntity<ProductModel> {
        return ResponseEntity(modeloService.updateName(modelo), HttpStatus.OK)
    }
    /*codigo para mostrar la lista del dto en ell controller*/
    fun listDto(): ResponseEntity<*>{
        return ResponseEntity(modeloService.listDto(), HttpStatus.OK)
    }
}