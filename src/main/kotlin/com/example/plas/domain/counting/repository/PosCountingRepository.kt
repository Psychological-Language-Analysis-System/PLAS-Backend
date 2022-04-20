package com.example.plas.domain.counting.repository

import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.essay.entity.Essay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PosCountingRepository: JpaRepository<PosCounting, Long> {
    fun findByEssay(essay: Essay): PosCounting?
}