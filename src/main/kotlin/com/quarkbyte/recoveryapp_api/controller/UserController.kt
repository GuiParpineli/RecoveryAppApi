package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["user"], produces = [MediaType.APPLICATION_JSON_VALUE] )
class UserController(private val service: UserService) {

    @GetMapping("all")
    fun getAll() = service.getAll()

    @GetMapping
    fun getById(id:UUID) = service.getById(id)
}