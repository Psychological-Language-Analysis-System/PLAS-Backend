package com.example.plas.config

import com.example.plas.security.JwtProvider
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        web!!.ignoring()
            .antMatchers(
                "/**", "/favicon.ico", "/error", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs"
            )
    }

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().cors()

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers(
                "/**", "/favicon.ico", "/error", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs"
            ).permitAll()
            .anyRequest().authenticated()

        http.apply(JwtSecurityConfig(jwtProvider))
    }
}
