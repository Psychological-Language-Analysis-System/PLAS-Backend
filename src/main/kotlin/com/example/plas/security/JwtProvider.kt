package com.example.plas.security

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.account.repository.AccountRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class JwtProvider(
    @Value("\${jwt.accessTokenExpiration}") private val accessExpiration: Long,
    @Value("\${jwt.secret}") private val secretKey: String,
    private val accountRepository: AccountRepository
) {
    companion object {
        const val BEARER_PREFIX = "Bearer "
    }

    fun getAuthentication(token: String): Authentication {
        val authority = arrayListOf(SimpleGrantedAuthority("USER"))
        return UsernamePasswordAuthenticationToken(getIdFromToken(token), "", authority)
    }

    fun createAccessToken(account: Account): String {
        val expiration = Date()
        expiration.time += accessExpiration

        return Jwts.builder()
            .setSubject(account.id.toString())
            .setIssuedAt(Date())
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
    }

    fun getIdFromToken(token: String): Long {
        val token = removePrefix(token)
        return getClaimFromToken(token) { obj: Claims -> obj.subject }.toLong()
    }

    fun getAccountFromToken(token: String): Account? {
        val id = getIdFromToken(token)
        return accountRepository.findAccountById(id)
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    fun validateToken(token: String): Boolean {
        return isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    private fun removePrefix(token: String) = token.removePrefix(BEARER_PREFIX)
}