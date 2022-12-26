package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.model.UserApp
import com.quarkbyte.recoveryapp_api.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(value = ["user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(private val service: UserService) {

    @GetMapping("all")
    fun getAll() = service.getAll()

    @GetMapping
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)

    @PostMapping
    fun save(@RequestBody user: UserApp) = service.save(user)

    @PatchMapping
    fun update(@RequestBody user: UserApp) = service.update(user)

    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)
}