package com.quarkbyte.recoveryapp_api.controller

import com.quarkbyte.recoveryapp_api.exceptions.UserLoginException
import com.quarkbyte.recoveryapp_api.model.user.UserApp
import com.quarkbyte.recoveryapp_api.security.JwtUtil
import com.quarkbyte.recoveryapp_api.service.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(value = ["user"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val service: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
) {

    @GetMapping("all")
    fun getAll() = service.getAll()

    @GetMapping
    fun getById(@RequestParam("id") id: UUID) = service.getById(id)

    @PostMapping("/login")
    fun login(@RequestBody user: UserApp): ResponseEntity<*> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(user.username, user.password)
            )
        } catch (e: Exception) {
            throw UserLoginException("Usuario ou senha invalidos")
        }

        val userDetails: UserDetails = service.loadUserByUsername(user.username)
        val jwt: String = jwtUtil.generateToken(userDetails)
        return ResponseEntity.ok(jwt)
    }

    @PostMapping
    fun save(@RequestBody user: UserApp) = service.save(user)

    @PatchMapping
    fun update(@RequestBody user: UserApp) = service.update(user)

    @DeleteMapping
    fun delete(@RequestParam("id") id: UUID) = service.delete(id)
}