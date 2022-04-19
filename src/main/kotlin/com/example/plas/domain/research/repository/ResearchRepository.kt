package com.example.plas.domain.research.repository

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.entity.SendResearchDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ResearchRepository: JpaRepository<Research, Long> {
    fun findResearchById(id: Long): Research?

    fun findAllByAccount(account: Account, pageable: Pageable): Page<SendResearchDto>
}