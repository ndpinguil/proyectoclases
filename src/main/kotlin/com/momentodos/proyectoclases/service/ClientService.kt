package com.momentodos.proyectoclases.service

import com.momentodos.proyectoclases.model.ClientModel
import com.momentodos.proyectoclases.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {
    @Autowired
    lateinit var modeloRepository: ClientRepository

    fun list ():List<ClientModel>{
        return modeloRepository.findAll()
    }

    fun save(modelo: ClientModel): ClientModel{
        try{

            modelo.nui?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La materia no debe ser nula")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(modelo: ClientModel): ClientModel{
        try {
            modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):ClientModel?{
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
    fun updateName(modelo:ClientModel): ClientModel{
        try{
            val response = modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                id=modelo.id
                nui=modelo.nui
                full_name=modelo.full_name
                address=modelo.address
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}