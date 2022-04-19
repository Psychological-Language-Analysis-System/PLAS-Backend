package com.example.plas.config

import com.example.plas.security.JwtProvider
import com.example.plas.security.TokenAuthenticationFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(
    private val jwtProvider: JwtProvider
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(http: HttpSecurity?) {
        val tokenAuthenticationFilter = TokenAuthenticationFilter(jwtProvider)
        http!!.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter()::class.java)
    }
}