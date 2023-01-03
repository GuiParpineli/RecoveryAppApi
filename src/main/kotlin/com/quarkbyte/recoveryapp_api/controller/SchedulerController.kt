package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.user.Scheduler
import com.quarkbyte.recoveryapp_api.service.user.SchedulerService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["scheduler"], produces = [MediaType.APPLICATION_JSON_VALUE])
class SchedulerController(val service: SchedulerService) {

    @GetMapping("all")
    fun getAll() = service.getall()

    @PostMapping
    fun save(@RequestBody scheduler: Scheduler) = service.save(scheduler)

}