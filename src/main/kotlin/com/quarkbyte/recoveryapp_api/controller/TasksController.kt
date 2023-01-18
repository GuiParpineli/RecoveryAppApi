package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.dto.TaskInput
import com.quarkbyte.recoveryapp_api.service.user.TaskService
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
@RequestMapping(value = ["task"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TasksController(val service: TaskService) {

    @GetMapping
    fun getAll() = service.getAll()

    @PostMapping
    fun save(@RequestBody tasks: TaskInput) = service.save(tasks)


}