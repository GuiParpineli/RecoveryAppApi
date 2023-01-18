package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.dto.SchedulerDTO
import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.service.user.SchedulerService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["scheduler"], produces = [MediaType.APPLICATION_JSON_VALUE])
class SchedulerController(val service: SchedulerService) {

    @GetMapping("all")
    fun getAll() = service.getAll()

    @GetMapping("byUser")
    fun getByUserId(@RequestParam("id") id: UUID) = service.getByUserId(id)

    @PostMapping
    fun save(@RequestBody scheduler: SchedulerDTO) = service.save(scheduler)

    @DeleteMapping("deleteTask")
    fun deleteTask(@RequestParam("id") id: UUID, @RequestParam("schedulerId") schedulerId: UUID) =
        service.deleteTask(id, schedulerId)
}