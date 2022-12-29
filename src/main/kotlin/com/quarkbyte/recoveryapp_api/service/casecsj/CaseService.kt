package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.model.cases.CaseCSJ
import com.quarkbyte.recoveryapp_api.repository.CaseRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class CaseService(private val repository: CaseRepository) {

    fun getAll(): ResponseEntity<*> {
        val saved: MutableList<CaseCSJ> = repository.findAll()
        return ResponseEntity.ok(saved)
    }

    fun getAllByID(ids: List<UUID>): ResponseEntity<*> {
        val saved = repository.findAllById(ids)
        if (saved.isEmpty())
            throw ResourceNotFoundException("None cases founded")
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        if (repository.findById(id).isPresent)
            repository.deleteById(id)
        return ResponseEntity.ok("deleted successfully")
    }

}