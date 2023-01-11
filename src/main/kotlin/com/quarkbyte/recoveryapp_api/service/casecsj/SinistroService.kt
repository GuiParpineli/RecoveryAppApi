package com.quarkbyte.recoveryapp_api.service.casecsj

import com.quarkbyte.recoveryapp_api.exceptions.FinalDataException
import com.quarkbyte.recoveryapp_api.exceptions.ResourceNotFoundException
import com.quarkbyte.recoveryapp_api.exceptions.SaveErrorException
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.enums.csj.SinistroType
import com.quarkbyte.recoveryapp_api.repository.SinistroRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class SinistroService(
    private val repository: SinistroRepository,
) {
    private val logger: Logger = LoggerFactory.getLogger(SinistroService::class.java)

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
        logger.info(saved.toString())
        logger.info(sinistro.toString())
        var newSinistro: Sinistro? = null
        sinistro.observation?.forEach { saved.get().observation?.add(it) }
        try {
            if (saved.isPresent) {

                //SINISTRO TYPE VAI SER CHECADO E NAO PODE ATUALIZAR ALGUNS CAMPOS DEPENDENDO DO TIPO
                when (saved.get().sinistroType) {
                    SinistroType.FURTO_QUALIFICADO, SinistroType.ROUBO, SinistroType.FURTO_SIMPLES -> {

                            val copy = Sinistro(
                                id = saved.get().id,
                                date = saved.get().date,
                                stepCSJ = saved.get().stepCSJ,
                                resolutionDate = saved.get().resolutionDate,
                                value = saved.get().value,
                                observation = saved.get().observation,
                                coverageValue = saved.get().coverageValue,
                                resolutionType = saved.get().resolutionType,
                                initialTime = saved.get().initialTime,
                                sinistroType = saved.get().sinistroType,
                                sinistro.imeiStatus,
                                sinistro.boStatus,
                                sinistro.videoStatus,
                                sinistro.sinistroDate,
                                saved.get().franchise,
                                saved.get().franchiseTotalValue,
                                saved.get().discountValue,
                                saved.get().payment
                            )
                            logger.info("PRIMEIRO CASE: ${saved.get().observation.toString()}")
                            newSinistro = repository.saveAndFlush(copy)

                    }

                    SinistroType.DANO, SinistroType.DANO_DE_FABRICA -> {
                            val copy = Sinistro(
                                id = saved.get().id,
                                date = saved.get().date,
                                stepCSJ = saved.get().stepCSJ,
                                resolutionDate = sinistro.resolutionDate,
                                value = saved.get().value,
                                observation = saved.get().observation,
                                coverageValue = saved.get().coverageValue,
                                resolutionType = saved.get().resolutionType,
                                initialTime = saved.get().initialTime,
                                sinistroType = saved.get().sinistroType,
                                imeiStatus = saved.get().imeiStatus,
                                boStatus = saved.get().boStatus,
                                videoStatus = saved.get().videoStatus,
                                sinistroDate = saved.get().sinistroDate,
                                franchise = saved.get().franchise,
                                franchiseTotalValue = saved.get().franchiseTotalValue,
                                discountValue = saved.get().discountValue,
                                payment = sinistro.payment
                            )
                            logger.info("SEGUNDO CASE: ${saved.get().observation.toString()}")
                            newSinistro = repository.saveAndFlush(copy)
                    }

                    SinistroType.PERDA -> {
                        val copy = Sinistro(
                            saved.get().id,
                            saved.get().date,
                            sinistro.stepCSJ,
                            sinistro.resolutionDate,
                            saved.get().value,
                            saved.get().observation,
                            sinistro.coverageValue,
                            sinistro.resolutionType,
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
                        logger.info("TERCEIRO CASE: ${saved.get().observation.toString()}")
                        newSinistro = repository.saveAndFlush(copy)
                    }
                }
            } else {
                throw ResourceNotFoundException("Nenhum Sinistro encontrado")
            }
        } catch (e: Exception) {
            throw SaveErrorException("Error, not saved")
        }
        logger.info("Como foi salvo: $newSinistro")
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