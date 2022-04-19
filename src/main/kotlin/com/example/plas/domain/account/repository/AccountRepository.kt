package com.example.plas.domain.account.repository

import com.example.plas.domain.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?
    fun findAccountById(id: Long): Account?
}