package com.quarkbyte.recoveryapp_api.service.customer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.customer.Bondsman
import com.quarkbyte.recoveryapp_api.model.dto.BondsmanDTO
import com.quarkbyte.recoveryapp_api.repository.BondsmanRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class BondsmanService(
    val repository: BondsmanRepository,
    val mapper: ObjectMapper
) {

    @Throws(ResourceNotFoundException::class)
    fun getAll(): ResponseEntity<*> {
        val saved = repository.findAll()
        val bondsmanDTOS: MutableList<BondsmanDTO> = ArrayList()
        if (saved.isEmpty()) throw ResourceNotFoundException("None Bondsman founded")
        saved.forEach { c -> bondsmanDTOS.add(mapper.convertValue(c)) }
        return ResponseEntity.ok(bondsmanDTOS)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None address founded") }!!
        return ResponseEntity.ok(saved)
    }

    fun getByEmailorCpf(email: String, cpf: String): ResponseEntity<*> {
        val founded = repository.findByEmailOrCpf(email, cpf)
        if (founded?.id == null)
            throw ResourceNotFoundException("None Bondman's founded")
        return ResponseEntity.ok(founded)
    }

    fun getByName(name: String): ResponseEntity<*> {
        val founded = repository.findByNameContainingIgnoreCase(name)
        if (founded!!.isEmpty())
            throw ResourceNotFoundException("None Bondman's founded")
        return ResponseEntity.ok(founded)
    }

    @Throws(SaveErrorException::class)
    fun save(bondsman: Bondsman): ResponseEntity<*> {
        val saved: Bondsman =
            try { repository.save(bondsman) }
            catch (e: Exception) { throw SaveErrorException("Error, not saved") }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(bondsman: Bondsman): ResponseEntity<*> {
        var newBondsman: Bondsman? = null
        val founded =
            repository.findByEmailOrCpf(bondsman.email, bondsman.cpf)
                ?: throw ResourceNotFoundException("None Bondsmans founded")
        try {
            if (founded.id != null) {
                val copy = Bondsman(
                    founded.id,
                    founded.name,
                    founded.lastName,
                    founded.cpf,
                    bondsman.phone,
                    bondsman.address,
                    founded.email,
                    founded.birthDay,
                    founded.gender,
                    founded.nationality
                )
                newBondsman = repository.saveAndFlush(copy)
            }
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(newBondsman)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        if (repository.findById(id).isPresent)
            repository.deleteById(id)
        return ResponseEntity.ok("deleted successfully")
    }
}