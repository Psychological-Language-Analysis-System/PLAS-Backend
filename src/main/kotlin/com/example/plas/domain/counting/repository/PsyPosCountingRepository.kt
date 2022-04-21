package com.example.plas.domain.counting.repository

import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.counting.entity.PsyPosCounting
import com.example.plas.domain.essay.entity.Essay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PsyPosCountingRepository: JpaRepository<PsyPosCounting, Long> {
    fun findByEssay(essay: Essay): PsyPosCounting?

}