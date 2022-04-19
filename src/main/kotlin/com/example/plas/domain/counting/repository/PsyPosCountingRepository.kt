package com.example.plas.domain.counting.repository

import com.example.plas.domain.counting.entity.PsyPosCounting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PsyPosCountingRepository: JpaRepository<PsyPosCounting, Long> {
}