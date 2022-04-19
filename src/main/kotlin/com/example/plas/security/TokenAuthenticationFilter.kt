package com.example.plas.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter(
    private val jwtProvider: JwtProvider
): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletResponse = response as HttpServletResponse
        try {
            val token = resolveToken(request as HttpServletRequest)
            if(!jwtProvider.validateToken(token))
                SecurityContextHolder.getContext().authentication = jwtProvider.getAuthentication(token)
            chain!!.doFilter(request, response)
        } catch (e: RuntimeException) {
            httpServletResponse.contentType = "application/json;charset=UTF-8"
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "unauthorized token")
        }
    }

    private fun resolveToken(request: HttpServletRequest): String {
        var token: String? = null
        for(c in request.cookies) {
            if(c.name == "accessToken")
                token = c.value
        }
        println(token)
        return token ?: throw RuntimeException("오류")
    }
}