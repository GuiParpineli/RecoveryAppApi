package com.quarkbyte.recoveryapp_api.security

import com.quarkbyte.recoveryapp_api.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtRequestFilter(val userRepository: UserRepository, val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")
        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7)
            username = jwtUtil.extractUserName(jwt)
        }

        if (username !== null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = userRepository.findByUsername(username)

            if (jwtUtil.validateToken(jwt!!, userDetails)) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}