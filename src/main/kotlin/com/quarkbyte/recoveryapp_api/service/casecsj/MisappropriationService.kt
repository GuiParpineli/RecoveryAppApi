package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.enums.csj.InternalStatus
import com.quarkbyte.recoveryapp_api.repository.MisappropriationRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class MisappropriationService(val repository: MisappropriationRepository) {

    fun get(): ResponseEntity<*> {
        val saved = repository.findAll()
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun getById(id: UUID): ResponseEntity<*> {
        val saved = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("None cases founded") }!!
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun save(misappropriation: Misappropriation): ResponseEntity<*> {
        if (misappropriation.date > misappropriation.resolutionDate)
            throw FinalDataException("Resolution date cannot be less than start date")
        val saved: Misappropriation = try {
            repository.save(misappropriation)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(SaveErrorException::class)
    fun update(misappropriation: Misappropriation): ResponseEntity<*> {
        if (misappropriation.date > misappropriation.resolutionDate)
            throw FinalDataException("Resolution date cannot be less than start date")
        val saved: Misappropriation = try {
            repository.saveAndFlush(misappropriation)
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val founded = repository.findById(id)
        if (founded.isPresent)
            if (founded.get().internalStatus == InternalStatus.FINALIZADO)
                repository.deleteById(id)
        return ResponseEntity.ok("deleted successfully")
    }
}