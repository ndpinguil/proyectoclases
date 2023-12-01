package com.momentodos.proyectoclases.service

import com.momentodos.proyectoclases.model.InvoiceModel
import com.momentodos.proyectoclases.model.ClientModel
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

    private fun validateInvoiceModel (invoiceModel: InvoiceModel) {
        invoiceModel.apply {
            code?.takeIf { it.trim().isNotEmpty() }
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El código de la factura no debe ser nulo o vacío")

            create_at?.takeIf { it.trim().isNotEmpty() }
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de creación no debe ser nula o vacía")

            total?.takeIf { it >= 0 }
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El total de la factura no debe ser negativo")
        }
    }

    /*fun listByTotal(value: Double): List<InvoiceModel>{ // Definir el SQL nativo - Ejemplo Factura
        return invoiceRepository.findAll()
    }*/
    fun filterTotal(value:Double?): List<InvoiceModel>? {
        return modeloRepository.filterTotal(value)
    }
}
