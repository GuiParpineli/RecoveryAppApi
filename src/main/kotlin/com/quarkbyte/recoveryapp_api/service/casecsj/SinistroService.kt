package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.repository.SinistroRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SinistroService(private val repository: SinistroRepository) {

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
    fun save(sinistro: Sinistro): ResponseEntity<*> {
        if (sinistro.date > sinistro.resolutionDate)
            throw FinalDataException("Resolution date cannot be less than start date")
        val saved: Sinistro = try {
            repository.save(sinistro)
        } catch (ex: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(saved)
    }

    fun update(sinistro: Sinistro): ResponseEntity<*> {
        val saved = repository.findById(sinistro.id!!)
        var newSinistro: Sinistro? = null
        try {
            if (saved.isPresent)
                if (!(saved.get().boStatus && saved.get().imeiStatus &&
                            saved.get().videoStatus)) {
                    val copy = Sinistro(
                            saved.get().id,
                            saved.get().date,
                            saved.get().stepCSJ,
                            saved.get().resolutionDate,
                            saved.get().value,
                            saved.get().coverageValue,
                            saved.get().resolutionType,
                            saved.get().recidivistCustomer,
                            saved.get().initialTime,
                            saved.get().sinistroType,
                            sinistro.imeiStatus,
                            sinistro.boStatus,
                            sinistro.videoStatus,
                            sinistro.sinistroDate,
                            saved.get().franchise,
                            saved.get().franchiseTotalValue,
                            saved.get().discountValue,
                            saved.get().payment
                        )
                    newSinistro = repository.saveAndFlush(copy)
                } else {
                    newSinistro = repository.saveAndFlush(sinistro)
                }
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(newSinistro)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        if (repository.findById(id).isPresent)
            repository.deleteById(id)
        return ResponseEntity.ok("Deleted successfully")
    }
}