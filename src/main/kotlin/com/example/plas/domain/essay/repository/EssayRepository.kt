package com.example.plas.domain.essay.repository

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.research.entity.Research
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EssayRepository: JpaRepository<Essay, Long> {
    fun findAllEssayByResearch(research: Research, pageable: Pageable): Page<Essay.SendEssayDto>
    fun findAllEssayByResearch(research: Research): ArrayList<Essay>
    fun findEssayById(id: Long): Essay?
}