package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.cases.Misappropriation
import com.quarkbyte.recoveryapp_api.model.cases.Sinistro
import com.quarkbyte.recoveryapp_api.model.cases.TechnicalSupport
import com.quarkbyte.recoveryapp_api.service.casecsj.CaseService
import com.quarkbyte.recoveryapp_api.service.casecsj.MisappropriationService
import com.quarkbyte.recoveryapp_api.service.casecsj.SinistroService
import com.quarkbyte.recoveryapp_api.service.casecsj.TechnicalSupportService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["case"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CaseController(
    private val service: CaseService,
    private val sinsitroService: SinistroService,
    private val misappropriationService: MisappropriationService,
    private val technicalSupportService: TechnicalSupportService
) {
    @GetMapping
    fun getALl(): ResponseEntity<*> = service.getAll()

    @GetMapping("sinistro/all")
    fun getAllSinistro() = sinsitroService.get()

    //gets for each case

    @GetMapping("misappropriation/all")
    fun getAllMisappropriation() = misappropriationService.get()

    @GetMapping("technicalsupport/all")
    fun getTechnicalSuport() = technicalSupportService.get()

    //gets by id each case
    @GetMapping("sinistro")
    fun getSinistroById(@RequestParam("id") id: UUID) = sinsitroService.getById(id)
    @GetMapping("misappropriation")
    fun getMisappropriationById(@RequestParam("id") id: UUID) = misappropriationService.getById(id)
    @GetMapping("technicalsupport")
    fun getTechnicalSupport(@RequestParam("id") id: UUID) = technicalSupportService.getById(id)

    //post for each case
    @PostMapping("saveSinistro")
    fun saveSinistro(@RequestBody sinistro: Sinistro) = sinsitroService.save(sinistro)

    @PostMapping("saveMisappopriation")
    fun saveMisappropriation(@RequestBody misappropriation: Misappropriation) =
        misappropriationService.save(misappropriation)

    @PostMapping("technicalsupport")
    fun saveTechnicalSupport(@RequestBody technicalSupport: TechnicalSupport) =
        technicalSupportService.save(technicalSupport)

    @PatchMapping("updatesinistro")
    fun updateSinistro(@RequestBody sinistro: Sinistro) = sinsitroService.update(sinistro)

    @PatchMapping("updatemisappropriation")
    fun updateMisappropriation(@RequestBody misappropriation: Misappropriation) =
        misappropriationService.update(misappropriation)

    @PatchMapping("updatetechnicalsupport")
    fun updateTechnicalSupport(@RequestBody technicalSupport: TechnicalSupport) =
        technicalSupportService.update(technicalSupport)

    @DeleteMapping("deletesinistro")
    fun deleteSinistro(@PathVariable("id") id: UUID) = sinsitroService.delete(id)

    @DeleteMapping("deletemisappropriation")
    fun deleteMisappropriation(@PathVariable("id") id: UUID) = misappropriationService.delete(id)

    @DeleteMapping("deletetechnicalsupport")
    fun deleteTechnicalSupport(@PathVariable("id") id: UUID) = technicalSupportService.delete(id)
}