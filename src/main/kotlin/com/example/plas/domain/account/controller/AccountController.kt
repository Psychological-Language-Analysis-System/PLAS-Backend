package com.example.plas.domain.account.controller

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.account.service.AccountService
import com.example.plas.security.CookieUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class AccountController(
    private val accountService: AccountService,
    private val cookieUtils: CookieUtils
) {
    @PostMapping("/login")
    fun login(@RequestBody loginInformation: Account.LoginInformation, httpResponse: HttpServletResponse): ResponseEntity<Long> {
        val token = accountService.loginByOAuth(loginInformation)
        val cookie = cookieUtils.buildCookieIncludeToken(token.accessToken)
        httpResponse.setHeader("Set-Cookie", cookie.toString())
        httpResponse.setHeader("Access-Control-Allow-Headers",
            "Date, Content-Type, Accept, X-Requested-With, Authorization, From, X-Auth-Token, Request-Id");
        httpResponse.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.addHeader("Set-Cookie", cookie.toString())
        return ResponseEntity.status(HttpStatus.OK).body(token.id)
    }
}