package com.momentodos.proyectoclases.service

import com.momentodos.proyectoclases.model.InvoiceModel
import com.momentodos.proyectoclases.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var modeloRepository: InvoiceRepository

    fun list ():List<InvoiceModel>{
        return modeloRepository.findAll()
    }

    fun save(modelo: InvoiceModel): InvoiceModel {
        try{

            modelo.code?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La materia no debe ser nula")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(modelo: InvoiceModel): InvoiceModel {
        try {
            modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): InvoiceModel?{
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
    fun updateName(modelo: InvoiceModel): InvoiceModel {
        try{
            val response = modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                id=modelo.id
                code=modelo.code
                create_at=modelo.create_at
                total=modelo.total
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}
