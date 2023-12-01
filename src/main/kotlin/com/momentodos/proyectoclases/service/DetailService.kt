package com.momentodos.proyectoclases.service


import com.momentodos.proyectoclases.model.DetailModel
import com.momentodos.proyectoclases.repository.DetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var modeloRepository: DetailRepository

    fun list ():List<DetailModel>{
        return modeloRepository.findAll()
    }

    fun save(modelo: DetailModel): DetailModel {
        try{

            modelo.price?.takeIf { it.trim().isNotEmpty()}
                ?:throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La materia no debe ser nula")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }
    }

    fun update(modelo: DetailModel): DetailModel {
        try {
            modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")

            return modeloRepository.save(modelo)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?): DetailModel?{
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
    fun updateName(modelo: DetailModel): DetailModel {
        try{
            val response = modeloRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                id=modelo.id
                price=modelo.price
            }
            return modeloRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}