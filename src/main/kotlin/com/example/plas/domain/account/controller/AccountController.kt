package com.example.plas.domain.account.controller

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.account.service.AccountService
import com.example.plas.security.CookieUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {
    @PostMapping
    fun login(@RequestBody loginInformation: Account.LoginInformation, httpResponse: HttpServletResponse): ResponseEntity<Account.Token> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.loginByOAuth(loginInformation))
    }
}