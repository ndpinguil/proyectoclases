package com.momentodos.proyectoclases.controller

import com.momentodos.proyectoclases.model.DetailModel
import com.momentodos.proyectoclases.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/DetailModel")
class DetailController {
    @Autowired
    lateinit var modeloService: DetailService

    @GetMapping
    fun list(): List<DetailModel> {
        return modeloService.list()
    }

    @PostMapping
    fun save(@RequestBody modelo: DetailModel): ResponseEntity<DetailModel> {
        return ResponseEntity(modeloService.save(modelo), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody modelo: DetailModel): ResponseEntity<DetailModel> {
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
    fun updateName (@RequestBody modelo: DetailModel): ResponseEntity<DetailModel> {
        return ResponseEntity(modeloService.updateName(modelo), HttpStatus.OK)
    }
}