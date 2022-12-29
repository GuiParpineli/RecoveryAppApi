package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.enums.csj.SinistroType
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
            //SINISTRO TYPE VAI SER CHECADO E NAO PODE ATUALIZAR ALGUNS CAMPOS DEPENDENDO DO TIPO
                when (saved.get().sinistroType) {
                    SinistroType.FURTO_QUALIFICADO, SinistroType.ROUBO, SinistroType.FURTO_SIMPLES ->
                        if (!(saved.get().boStatus && saved.get().imeiStatus && saved.get().videoStatus)) {
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
                        }

                    SinistroType.DANO, SinistroType.DANO_DE_FABRICA ->
                        if (!(saved.get().payment)) {
                            val copy = Sinistro(
                                saved.get().id,
                                saved.get().date,
                                saved.get().stepCSJ,
                                sinistro.resolutionDate,
                                saved.get().value,
                                saved.get().coverageValue,
                                saved.get().resolutionType,
                                saved.get().recidivistCustomer,
                                saved.get().initialTime,
                                saved.get().sinistroType,
                                saved.get().imeiStatus,
                                saved.get().boStatus,
                                saved.get().videoStatus,
                                saved.get().sinistroDate,
                                saved.get().franchise,
                                saved.get().franchiseTotalValue,
                                saved.get().discountValue,
                                sinistro.payment
                            )
                            newSinistro = repository.saveAndFlush(copy)
                        }

                    SinistroType.PERDA -> {
                        val copy = Sinistro(
                            saved.get().id,
                            saved.get().date,
                            sinistro.stepCSJ,
                            sinistro.resolutionDate,
                            saved.get().value,
                            sinistro.coverageValue,
                            sinistro.resolutionType,
                            sinistro.recidivistCustomer,
                            sinistro.initialTime,
                            sinistro.sinistroType,
                            sinistro.imeiStatus,
                            sinistro.boStatus,
                            sinistro.videoStatus,
                            sinistro.sinistroDate,
                            sinistro.franchise,
                            sinistro.franchiseTotalValue,
                            sinistro.discountValue,
                            sinistro.payment
                        )
                        newSinistro = repository.saveAndFlush(copy)
                    }
                }
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        return ResponseEntity.ok(newSinistro)
    }

    @Throws(ResourceNotFoundException::class)
    fun delete(id: UUID): ResponseEntity<*> {
        val founded = repository.findById(id)
        if (founded.isPresent)
            repository.deleteById(founded.get().id!!)
        return ResponseEntity.ok("Deleted successfully")
    }

}